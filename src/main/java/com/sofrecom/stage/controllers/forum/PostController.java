package com.sofrecom.stage.controllers.forum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofrecom.stage.models.Post;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.repository.forum.PostRepository;
import com.sofrecom.stage.services.forum.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/*import tn.esprit.pidev.entities.Post;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.UserRepository;
import tn.esprit.pidev.repositories.forum.PostRepository;
import tn.esprit.pidev.services.forum.IPostService;
*/
import java.io.IOException;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class PostController {


    @Autowired
    private IPostService postService;

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private IUtilidateurRepo userRepository;

    @RequestMapping(value = "/addPostFile/{userId}" ,method = RequestMethod.POST )
    public Post addPost(@PathVariable("userId") Long userId, @RequestParam("file") MultipartFile file, @RequestParam("details") String post) throws IOException {
        if(file == null){
            throw new NullPointerException("We cannot save null ! ");
        }
        Post pDetails = new ObjectMapper().readValue(post,Post.class);
        System.out.println(pDetails);
        Post p = postService.savePostFile(userId,file,pDetails);
        return p;
    }

    @RequestMapping(value = "/getPost/{postId}" ,method = RequestMethod.GET )
    public Post getPostById(@PathVariable("postId") Long postId){
       return postService.retrievePostById(postId);
    }


    @RequestMapping(path = { "/updatePostText/{postId}" },method = RequestMethod.PUT)
    public Post modifyPostText(@PathVariable("postId") Long postId, @RequestBody Post post){
        if(post == null){
            throw new NullPointerException("We cannot update null");
        }
        return postService.updatePost(postId,post);
    }

    @RequestMapping(path = { "/updatePostFile/{postId}" },method = RequestMethod.PUT)
    public Post modifyPostFile(@PathVariable("postId") Long postId, @RequestParam MultipartFile file){
        return postService.updatePostFile(postId,file);
    }

    @RequestMapping(value = "/addTextPost/{userId}" ,method = RequestMethod.POST )
    public Post addTextPost(@PathVariable("userId") Long userId,@RequestBody  Post post){
        if(post ==null){
            throw new NullPointerException("we cannot save null ! ");
        }
        return postService.saveTextPost(userId,post);
    }



    @RequestMapping(path = { "/deletePost/{postId}" },method = RequestMethod.DELETE)
    public void deletePost(@PathVariable("postId") Long postId){

        postService.deletePost(postId);
    }


    @RequestMapping(path = { "/getPostsByUserPreferences/{userId}" },method = RequestMethod.GET)
    public Set<Post> getPostsByUserPreferences(@PathVariable("userId") Long userId){
        UserInformation user = userRepository.findById(userId).orElse(null);
        Set<String> userPreferences = user.getUserPreferences();
        System.out.println("********");
        System.out.println("User prefernces are : "+ userPreferences);
        System.out.println("********");
        return postService.getPostsByUserPreferences(userPreferences);
    }


    @RequestMapping(path = { "/getBestPostsByUserPreferences/{userId}" },method = RequestMethod.GET)
    public Set<Post> getBestPostsByUserPreferences(@PathVariable("userId") Long userId){
        UserInformation user = userRepository.findById(userId).orElse(null);
        Set<String> userPreferences = user.getUserPreferences();
        System.out.println("********");
        System.out.println("User prefernces are : "+ userPreferences);
        System.out.println("********");
        return postService.getBestPostsByUserPreferences(userPreferences);
    }




}
