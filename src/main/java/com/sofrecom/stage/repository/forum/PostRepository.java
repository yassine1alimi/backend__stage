package com.sofrecom.stage.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Post;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value="select * from post where  post_category in(?1) order by post_creation_date desc",nativeQuery = true)
    public Set<Post> getPostsByUserPreferences(Set<String> userPreferences);

    @Query(value="select * from post where  post_category in(?1) and post_rating_score>3 order by post_rating_score desc",nativeQuery = true)
    public Set<Post> getBestPostsByUserPreferences(Set<String> userPreferences);



}
