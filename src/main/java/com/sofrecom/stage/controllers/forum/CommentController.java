package com.sofrecom.stage.controllers.forum;


import com.sofrecom.stage.models.Comment;
import com.sofrecom.stage.repository.forum.CommentRepository;
import com.sofrecom.stage.services.forum.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/*import tn.esprit.pidev.entities.Comment;
import tn.esprit.pidev.repositories.forum.CommentRepository;
import tn.esprit.pidev.services.forum.ICommentService;
*/
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/addComment" ,method = RequestMethod.POST )
    public void addMultiPartComment(@RequestParam("file") MultipartFile file){
        commentService.saveMultiPartComment(file);
    }

   /* @GetMapping(path = { "/getComment/{commentId}" })
    public Comment getVideoByName(@PathVariable("commentId") String commentId){
        Comment retrievedComment = commentService.retrieveCommentById(commentId);
        return retrievedComment;
    }*/

    @RequestMapping(value = "/addTextComment" ,method = RequestMethod.POST )
    public void addTextComment(@RequestBody Comment comment){
        commentService.saveTextComment(comment);
    }

    /*@GetMapping(path = { "/getImage/{commentId}" })
    public Optional<Comment> getImage(@PathVariable("commentId") String commentId) throws IOException {

        final Optional<Comment> retrievedImage = commentRepository.findById(commentId);
        return retrievedImage;
    }*/

    @RequestMapping(path = { "/deleteComment/{commentId}" },method = RequestMethod.DELETE)
    public void deleteCommentById(@PathVariable("commentId") String commentId){
            commentService.removeCommentById(commentId);
    }


    @RequestMapping(path = { "/updateComment/{commentId}" },method = RequestMethod.PUT)
    public Comment  modifyComment(@PathVariable("commentId") String commentId,@RequestBody Comment comment){
        if(comment==null){
            throw new NullPointerException("we cannot update null !");
        }
        return commentService.updateComment(commentId,comment);
    }

    @RequestMapping(path = { "/updateCommentFile/{commentId}" },method = RequestMethod.PUT)
    public Comment  modifyCommentFile(@PathVariable("commentId") String commentId,@RequestParam("file") MultipartFile file){

        return commentService.updateCommentFile(commentId,file);
    }







}
