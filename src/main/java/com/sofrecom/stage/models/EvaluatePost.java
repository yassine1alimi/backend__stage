package com.sofrecom.stage.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EvaluatePost implements Serializable {
    @EmbeddedId
    private EvaluatePostKey evaluatePostKey;
    @Enumerated(EnumType.STRING)
    private Emoji emoji;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    @ManyToOne
    @JsonIgnore
    @MapsId("userId")

    @JoinColumn(name = "user_id")
    private UserInformation postEvaluator;
    @ManyToOne
    @JsonIgnore
    @MapsId("postId")
    @JoinColumn(name= "post_id")
    private Post evaluatedPost;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean locked;

    public EvaluatePostKey getEvaluatePostKey() {
        return evaluatePostKey;
    }

    public void setEvaluatePostKey(EvaluatePostKey evaluatePostKey) {
        this.evaluatePostKey = evaluatePostKey;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public UserInformation getPostEvaluator() {
        return postEvaluator;
    }

    public void setPostEvaluator(UserInformation postEvaluator) {
        this.postEvaluator = postEvaluator;
    }

    public Post getEvaluatedPost() {
        return evaluatedPost;
    }

    public void setEvaluatedPost(Post evaluatedPost) {
        this.evaluatedPost = evaluatedPost;
    }


    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "EvaluatePost{" +
                "evaluatePostKey=" + evaluatePostKey +
                ", emoji=" + emoji +
                ", rating=" + rating +
                ", postEvaluator=" + postEvaluator +
                ", evaluatedPost=" + evaluatedPost +
                ", comment=" + comment +
                ", locked=" + locked +
                '}';
    }
}
