package com.sofrecom.stage.services.forum;

import com.sofrecom.stage.models.Category;
import com.sofrecom.stage.models.Post;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.repository.forum.PostRepository;
import com.sofrecom.stage.utils.HttpClientMain;
import com.sofrecom.stage.utils.PosthttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IUtilidateurRepo userRepository;




    @Override
    public Post savePostFile(Long userId, MultipartFile file, Post postDetails) {
        Post post = new Post();
        try {
            post.setPostTitle(postDetails.getPostTitle());
            post.setPostContentType(file.getContentType());
            post.setPostContent(file.getBytes());
            post.setPostCreationDate(new Timestamp(new Date().getTime()));
            post.setNbLikes(0);
            post.setPostCreator(userRepository.findById(userId).orElse(null));
            System.out.println(post.getPostCreator().toString());
            Post addedPost = postRepository.save(post);
            PosthttpClient client = new PosthttpClient();
            client.postRequest("https://imageclassifierpidev.herokuapp.com/sendImage",addedPost);
            HttpClientMain httpClientMain = new HttpClientMain("https://imageclassifierpidev.herokuapp.com/getResult");
            httpClientMain.init();
            httpClientMain.getData();
            String category = httpClientMain.getPostCategory();
            System.out.println("category of post will appended to the user list : "+category);
            httpClientMain.closeConnection();
            UserInformation postCreator =  userRepository.findById(userId).orElse(null);
            postCreator.getUserPreferences().add(category);
            System.out.println(postCreator.getUserPreferences());
            switch (category){
                case "events":
                    post.setPostCategory(Category.EVENTS);
                    break;
                case "emotional":
                    post.setPostCategory(Category.EMOTIONAL);
                    break;
                case "psycoart":
                    post.setPostCategory(Category.PSYCOART);
                    break;
                case "suffering":
                    post.setPostCategory(Category.SUFFERING);
                    break;
            }
            postRepository.save(post);
            return addedPost;

        } catch (IOException e) {
            System.out.println(e);
        }
        return null;    }

    @Override
    public Post saveTextPost(Long userId, Post post) {
        if(post ==null){
            throw new NullPointerException("we cannot save null ! ");
        }
        Post p = new Post();
        p.setNbLikes(0);
        p.setPostTitle(post.getPostTitle());
        p.setPostDescription(post.getPostDescription());
        p.setPostContent(post.getPostContent());
        p.setPostCreator(userRepository.findById(userId).orElse(null));
        p.setPostCreationDate(new Timestamp(new Date().getTime()));
        p.setPostContentType("text/plain");
        return postRepository.save(p);
    }

    @Override
    public Post updatePost(long postId, Post post) {
        Post retrievedPost = retrievePostById(postId);
        retrievedPost.setPostContent(post.getPostContent());
        retrievedPost.setPostTitle(post.getPostTitle());
        retrievedPost.setPostDescription(post.getPostDescription());
        retrievedPost.setPostUpdateDate(new Timestamp(new Date().getTime()));
        return postRepository.save(retrievedPost);
    }

    @Override
    public Post updatePostFile(Long postId, MultipartFile file) {
        Post p = retrievePostById(postId);
        try{

            p.setPostContentType(file.getContentType());
            p.setPostContent(file.getBytes());
            p.setPostUpdateDate(new Timestamp(new Date().getTime()));
            if(file.getContentType().contains("image")){
                PosthttpClient client = new PosthttpClient();
                client.postRequest("https://imageclassifierpidev.herokuapp.com/sendImage",p);
                HttpClientMain httpClientMain = new HttpClientMain("https://imageclassifierpidev.herokuapp.com/getResult");
                httpClientMain.init();
                httpClientMain.getData();
                String category = httpClientMain.getPostCategory();
                httpClientMain.closeConnection();
                UserInformation postCreator =  userRepository.findById(p.getPostCreator().getIdUser()).orElse(null);
                postCreator.getUserPreferences().add(category);
                System.out.println(postCreator.getUserPreferences());
                System.out.println("category of post will appended to the user list : "+category);
                switch (category){
                    case "events":
                        p.setPostCategory(Category.EVENTS);
                        break;
                    case "emotional":
                        p.setPostCategory(Category.EMOTIONAL);
                        break;
                    case "psycoart":
                        p.setPostCategory(Category.PSYCOART);
                        break;
                    case "suffering":
                        p.setPostCategory(Category.SUFFERING);
                        break;
                }
                return postRepository.save(p);

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        //return postRepository.save(p);
        return null;
    }

    @Override
    public Post retrievePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        post.setLocked(true);
        postRepository.save(post);
    }

    @Override
    public Set<Post> getPostsByUserPreferences(Set<String> userPreferences) {
        return postRepository.getPostsByUserPreferences(userPreferences);
    }

    @Override
    public Set<Post> getBestPostsByUserPreferences(Set<String> userPreferences) {
        return postRepository.getBestPostsByUserPreferences(userPreferences);
    }
}
