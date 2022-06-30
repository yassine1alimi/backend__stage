package com.sofrecom.stage.services.forum;

import com.sofrecom.stage.models.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
public interface IPostService {
    Post savePostFile(Long userId, MultipartFile file, Post postDetails);
    Post saveTextPost(Long userId,Post post);
    Post updatePost(long postId,Post post);
    Post updatePostFile(Long postId, MultipartFile file);
    Post retrievePostById(Long post);
    void deletePost(Long postId);
    Set<Post> getPostsByUserPreferences(Set<String> userPreferences);
    Set<Post> getBestPostsByUserPreferences(Set<String> userPreferences);
}
