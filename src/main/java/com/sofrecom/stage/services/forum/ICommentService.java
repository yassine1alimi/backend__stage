package com.sofrecom.stage.services.forum;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sofrecom.stage.models.Comment;

@Service
public interface ICommentService {


    Comment saveMultiPartComment(MultipartFile file);
    Comment retrieveCommentById(String commentId);
    void removeCommentById(String commentId);
    Comment updateComment(String commentId,Comment comment);
    Comment updateCommentFile(String commentId,MultipartFile file);
    Comment saveTextComment(Comment comment);



}
