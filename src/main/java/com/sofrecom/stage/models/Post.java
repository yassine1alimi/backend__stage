package com.sofrecom.stage.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The id of the post
     */
    private Long postId;
    /**
     * The post title
     */
    private String postTitle;

    /**
     * The post title
     */
    private String postDescription;




    /**
     * The creation date of the post
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date postCreationDate;
    /**
     * The updating date of the post
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date postUpdateDate;
    /**
     * The post content (String|Image|Video...)
     */
    @Lob
    private byte[] postContent;
    /**
     * The number of likes for this post
     */
    private int nbLikes;
    /**
     * The Category which the post is belonging to
     */
    @Enumerated(EnumType.STRING)
    private Category postCategory;

    /**
     * the post contentType
     */
    private String postContentType;


     private float postRatingScore;


    /**
     * The post  is locked or not
     */
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean locked;



    @OneToMany(mappedBy = "evaluatedPost",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EvaluatePost> postEvaluations;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="user_id ")
    private UserInformation postCreator;


    /**
     * @return the post Id
     */
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
    /**
     * @return the post Title
     */
    public String getPostTitle() {
        return postTitle;
    }
    /**
     * @param postTitle the postTitle to set
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    /**
     * @return get post's creation date
     */
    public Date getPostCreationDate() {
        return postCreationDate;
    }
    /**
     * @param postCreationDate the postCreationDate to set
     */
    public void setPostCreationDate(Date postCreationDate) {
        this.postCreationDate = postCreationDate;
    }
    /**
     * @return get post's updating date
     */
    public Date getPostUpdateDate() {
        return postUpdateDate;
    }
    /**
     * @param postUpdateDate the postUpdateDate to set
     */
    public void setPostUpdateDate(Date postUpdateDate) {
        this.postUpdateDate = postUpdateDate;
    }
    /**
     * @return the post's content
     */
    public byte[] getPostContent() {
        return postContent;
    }
    /**
     * @param postContent the postContent to set
     */
    public void setPostContent(byte[] postContent) {
        this.postContent = postContent;
    }
    /**
     * @return get post's number of likes
     */
    public int getNbLikes() {
        return nbLikes;
    }
    /**
     * @param nbLikes the nbLikes to set
     */
    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }
    /**
     * @return get the post's category
     */
    public Category getPostCategory() {
        return postCategory;
    }
    /**
     * @param postCategory the postCategory to set
     */
    public void setPostCatgory(Category postCategory) {
        this.postCategory = postCategory;
    }


    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public void setPostCategory(Category postCategory) {
        this.postCategory = postCategory;
    }

    public Set<EvaluatePost> getPostEvaluations() {
        return postEvaluations;
    }

    public void setPostEvaluations(Set<EvaluatePost> postEvaluations) {
        this.postEvaluations = postEvaluations;
    }

    public UserInformation getPostCreator() {
        return postCreator;
    }

    public void setPostCreator(UserInformation postCreator) {
        this.postCreator = postCreator;
    }


    public String getPostContentType() {
        return postContentType;
    }

    public void setPostContentType(String postContentType) {
        this.postContentType = postContentType;
    }


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public float getPostRatingScore() {
        return postRatingScore;
    }

    public void setPostRatingScore(float postRatingScore) {
        this.postRatingScore = postRatingScore;
    }
}
