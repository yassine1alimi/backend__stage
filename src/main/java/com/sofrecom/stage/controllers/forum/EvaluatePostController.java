package com.sofrecom.stage.controllers.forum;

import com.sofrecom.stage.models.Comment;
import com.sofrecom.stage.models.EvaluatePost;
import com.sofrecom.stage.services.forum.IEvaluatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/*import tn.esprit.pidev.entities.*;
import tn.esprit.pidev.services.forum.IEvaluatePostService;
*/
import java.io.IOException;
import java.text.ParseException;
import java.util.Set;

@RestController
public class EvaluatePostController {

    @Autowired
    private IEvaluatePostService evaluatePostService;

    @RequestMapping(value = "/addEvaluatePostFile/{postId}/{userId}" ,method = RequestMethod.POST )
    public EvaluatePost addEvaluatePostFile(@RequestParam("file") MultipartFile file, @PathVariable("postId") Long postId, @PathVariable("userId") Long userId) throws IOException {

           return  evaluatePostService.addEvaluatePostFile(file,postId,userId);

    }

    @RequestMapping(value = "/addEvaluatePostText/{postId}/{userId}" ,method = RequestMethod.POST )
    public EvaluatePost addEvaluatePostText(@RequestBody Comment comment, @PathVariable("postId") Long postId, @PathVariable("userId") Long userId) throws IOException {

        return  evaluatePostService.addEvaluatePostText(comment,postId,userId);

    }
    @RequestMapping(path = { "/updateEvaluationPost/{postId}/{userId}/{interactionDate}"},method = RequestMethod.PUT)
    public EvaluatePost  modifyEvaluatePost(
            @PathVariable("postId") Long postId ,
            @PathVariable("userId") Long userId ,
            @PathVariable("interactionDate") String interactionDate ,
            @RequestBody EvaluatePost evaluatePost) throws ParseException {
        if(evaluatePost==null){
            throw new NullPointerException("we cannot update null !");
        }

        System.out.println("Interaction Date is : "+interactionDate);
        evaluatePost.getEvaluatePostKey().setPostId(postId);
        evaluatePost.getEvaluatePostKey().setUserId(userId);
        evaluatePost.getEvaluatePostKey().setInteractionDate(interactionDate);
        System.out.println("====================================");
        System.out.println(evaluatePost.toString());
        System.out.println("====================================");
        //return evaluatePostService.updateEvaluatePost(evaluatePost.getEvaluatePostKey(),evaluatePost);
        return null;
    }


    @RequestMapping(path = { "/evaluatePostRating/{postId}/{userId}"},method = RequestMethod.POST)
    public EvaluatePost  evaluatePostRating(
            @PathVariable("postId") Long postId ,
            @PathVariable("userId") Long userId ,
            @RequestBody EvaluatePost evaluatePost){

        return evaluatePostService.addEvaluationPostRating(evaluatePost,postId,userId);
    }


    @RequestMapping(path = { "/evaluatePostEmoji/{postId}/{userId}"},method = RequestMethod.POST)
    public EvaluatePost evaluatePostEmoji(
            @PathVariable("postId") Long postId ,
            @PathVariable("userId") Long userId ,
            @RequestBody EvaluatePost evaluatePost){

        return evaluatePostService.addEvaluationPostEmoji(evaluatePost,postId,userId);
    }


    @RequestMapping(path = { "/getAllEvaluationPostByUserId/{userId}"},method = RequestMethod.GET)
    public Set<EvaluatePost> evaluatePostByUserId(@PathVariable("userId") Long userId){

        return evaluatePostService.getEvaluationPostByUserId(userId);
    }


    @RequestMapping(path = { "/getAllEvaluationPostByPostId/{postId}"},method = RequestMethod.GET)
    public Set<EvaluatePost> evaluatePostByPostId(@PathVariable("postId") Long postId){

        return evaluatePostService.getAllEvaluationPostByPostId(postId);
    }


    @RequestMapping(path = { "/evaluatePostRatingByPostId/{postId}"},method = RequestMethod.GET)
    public String evaluatePostRatingByPostId(@PathVariable("postId") Long postId){

        return evaluatePostService.getPostDetails(postId);
    }




}
