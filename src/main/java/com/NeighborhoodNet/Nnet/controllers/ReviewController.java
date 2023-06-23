package com.NeighborhoodNet.Nnet.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NeighborhoodNet.Nnet.dtos.requests.ReviewBack;
import com.NeighborhoodNet.Nnet.dtos.requests.ReviewRequest;
import com.NeighborhoodNet.Nnet.dtos.responces.ReviewsResponse;
import com.NeighborhoodNet.Nnet.services.JwtTokenService;
import com.NeighborhoodNet.Nnet.services.ReviewService;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
        private final JwtTokenService tokenService;
        private final ReviewService reviewService;

    @PostMapping("/add")
     public ResponseEntity<?>addReview(@RequestBody ReviewRequest req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        reviewService.createReview(req, user_id);
     
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
    
    }


    @GetMapping("/retrive")
    public ResponseEntity<List<ReviewsResponse>> retriveReviews(@RequestBody ReviewBack req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        List<ReviewsResponse> reviews = reviewService.getAll(req.getPostId(), user_id);


        return ResponseEntity.status(HttpStatus.OK).body(reviews);
        
    }

    @PutMapping("/like")
    public ResponseEntity<?>addlike(@RequestBody ReviewBack req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        reviewService.addLike(req, user_id);
     
    
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    
    }

    @DeleteMapping("/deletecomment")
    public ResponseEntity<?>deleteComment(@RequestBody ReviewBack req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        reviewService.deleteLike(req, user_id);
     
    
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    
    }

}
