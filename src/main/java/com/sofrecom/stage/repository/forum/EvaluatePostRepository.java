package com.sofrecom.stage.repository.forum;


import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.EvaluatePost;
import com.sofrecom.stage.models.EvaluatePostKey;

import java.util.Set;

@Repository
public interface EvaluatePostRepository  extends JpaRepository<EvaluatePost, EvaluatePostKey> {
    @Query(value="select * from evaluate_post ev where ev.user_id =?1 and ev.locked = false",nativeQuery=true)
     Set<EvaluatePost> getAllEvaluationPostByUserId(Long userId);


    @Query(value="select * from evaluate_post ev where ev.post_id =?1 and ev.locked=false",nativeQuery=true)
    Set<EvaluatePost> getAllEvaluationPostByPostId(Long postId);

    @Query(value="select * from evaluate_post ev where ev.post_id =?1 and ev.comment_id is not null and ev.locked=false",nativeQuery=true)
    Set<EvaluatePost> getCommentEvaluationPostByPostId(Long postId);

    @Query(value="select * from evaluate_post ev where ev.post_id =?1 and ev.locked=false",nativeQuery=true)
    Set<EvaluatePost>getReactionEvaluationPostByPostId(Long postId);



    @Modifying
    @Query(value="update evaluate_post set locked = true  where evaluate_post.comment_id =?1",nativeQuery=true)
    void  lockEvaluatePost(String userId);


    public interface PostDetails {

        String getCommentId();

       // Long getPostId();

    }






}
