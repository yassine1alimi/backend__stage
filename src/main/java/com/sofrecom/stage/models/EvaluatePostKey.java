package com.sofrecom.stage.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**

 *
 */
@Embeddable
public class EvaluatePostKey implements Serializable {


    @Column(name="post_id")
    private Long postId;
    @Column(name="user_id")
    private Long userId;

    private String interactionDate;


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(String interactionDate) {
        this.interactionDate = interactionDate;
    }

    @Override
    public String toString() {
        return "EvaluatePostKey{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", interactionDate=" + interactionDate +
                '}';
    }
}
