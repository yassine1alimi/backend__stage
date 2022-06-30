package com.sofrecom.stage.services.forum;

import com.sofrecom.stage.models.Comment;
import com.sofrecom.stage.repository.forum.CommentRepository;
import com.sofrecom.stage.repository.forum.EvaluatePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EvaluatePostRepository evaluatePostRepository;


    @Override
    public Comment saveMultiPartComment(MultipartFile file) {
        return null;
    }

    @Override
    public Comment retrieveCommentById(String commentId) {
        return null;
    }

    @Override
    public void removeCommentById(String commentId) {

    }

    @Override
    public Comment updateComment(String commentId, Comment comment) {
        return null;
    }

    @Override
    public Comment updateCommentFile(String commentId, MultipartFile file) {
        return null;
    }

    @Override
    public Comment saveTextComment(Comment comment) {
        return null;
    }
}
