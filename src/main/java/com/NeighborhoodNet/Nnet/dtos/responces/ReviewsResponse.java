package com.NeighborhoodNet.Nnet.dtos.responces;

import com.NeighborhoodNet.Nnet.entities.Review;
import com.NeighborhoodNet.Nnet.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewsResponse {
    
    String commentId;
    String username;
    String comment;
    int like;
    String userId;


    public ReviewsResponse(User user, Review review) {

        this.commentId = review.getId();
        this.username = user.getUsername();
        this.comment = review.getComment();
        this.like = review.getLikes();
        this.userId = review.getUser_id().getId();
    }

}
