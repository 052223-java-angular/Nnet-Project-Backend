package com.NeighborhoodNet.Nnet.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NeighborhoodNet.Nnet.dtos.requests.DeleteComment;
import com.NeighborhoodNet.Nnet.dtos.requests.DeleteContentRequest;
import com.NeighborhoodNet.Nnet.dtos.requests.Filter;
import com.NeighborhoodNet.Nnet.dtos.requests.NewPostRequest;
import com.NeighborhoodNet.Nnet.dtos.responces.Feed;
import com.NeighborhoodNet.Nnet.services.JwtTokenService;
import com.NeighborhoodNet.Nnet.services.PostService;
import com.NeighborhoodNet.Nnet.services.ReviewService;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.ResourceConflictException;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final JwtTokenService tokenService;
    private final ReviewService reviewService;

    @PostMapping("/add")
     public ResponseEntity<?>createPost(@RequestBody NewPostRequest req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        postService.createPost(req, user_id);
     
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
    
    }

    @GetMapping("/feed")
    public ResponseEntity<List<Feed>> feedPosts(HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        List<Feed> post = postService.getAll(user_id);


        return ResponseEntity.status(HttpStatus.OK).body(post);
        
    }

       @DeleteMapping("/deletePost")
     public ResponseEntity<?>deletePost(@RequestBody DeleteContentRequest req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }

        //check if the user id exist
        if(postService.isValidId(req.getPostId())){

            throw new ResourceConflictException("Can not find Post!");

        }

        // if(!tokenService.extractUserRole(token).equals("ADMIN")){
        //     throw new RoleNotFoundException("Unauthorized Access!!!");
        // }


        postService.removePost(req.getPostId());

    
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    
    }

    @DeleteMapping("/deletecomment")
     public ResponseEntity<?>deletecomment(@RequestBody DeleteComment req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //check if the user id exist
        // if(postService.isValidId(req.getComment())){

        //     throw new ResourceConflictException("Can not find Post!");

        // }

        // if(!tokenService.extractUserRole(token).equals("ADMIN")){
        //     throw new RoleNotFoundException("Unauthorized Access!!!");
        // }

        System.out.println("reached here");

        reviewService.removeComment(req.getComment());

    
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    
    }


     @GetMapping("/filter")
    public ResponseEntity<List<Feed>> filterfeed(@RequestBody Filter req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        List<Feed> post = postService.getFiltered(req.getCategory(), user_id);


        return ResponseEntity.status(HttpStatus.OK).body(post);
        
    }

    
}
