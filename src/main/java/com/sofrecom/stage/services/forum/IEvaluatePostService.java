package com.sofrecom.stage.services.forum;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sofrecom.stage.models.Comment;
import com.sofrecom.stage.models.EvaluatePost;

import java.util.Set;

@Service
public interface IEvaluatePostService {

    EvaluatePost addEvaluatePostFile(MultipartFile file,Long postId,Long userId);
    EvaluatePost addEvaluatePostText(Comment comment, Long postId, Long userId);
    EvaluatePost addEvaluationPostRating(EvaluatePost evaluatePost, Long postId, Long userId);
    EvaluatePost addEvaluationPostEmoji(EvaluatePost evaluatePost, Long postId, Long userId);
    Set<EvaluatePost> getEvaluationPostByUserId(Long UserId);
    Set<EvaluatePost> getAllEvaluationPostByPostId(Long postId);
    String getPostDetails(Long postId);

   // Set<EvaluatePost>getReactionEvaluationPostByPostId(Long postId);
    //EvaluatePost updateEvaluatePost(EvaluatePostKey evaluatePostKey, EvaluatePost evaluatePost);

}
