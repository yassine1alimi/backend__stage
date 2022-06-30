package com.sofrecom.stage.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Message is an entity which represent the Message table
 * @author Samti Med Wael
 *
 */

@Entity
public class Comment implements Serializable {


    /**
     * The Message Id
     */

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String commentId ;

    /**
     * The Comment content
     */
    @Lob
    private byte[] commentContent;

    /**
     * The Comment contentType
     */
    private String CommentContentType;

    /**
     * The Comment is locked or not
     */
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean locked;





    public String getCommentContentType() {
        return CommentContentType;
    }

    public void setCommentContentType(String commentContentType) {
        CommentContentType = commentContentType;
    }

    public Comment() {
    }

    /**
     * @return the comment Id
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    /**
     * @return the comment Content
     */
    public byte[] getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent the commentContent to set
     */
    public void setCommentContent(byte[] commentContent) {
        this.commentContent = commentContent;
    }


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", commentContent=" + Arrays.toString(commentContent) +
                ", CommentContentType='" + CommentContentType + '\'' +
                '}';
    }
}
