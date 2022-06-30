package com.sofrecom.stage.repository.forum;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
}
