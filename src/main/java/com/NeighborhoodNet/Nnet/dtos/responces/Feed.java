package com.NeighborhoodNet.Nnet.dtos.responces;

import java.time.LocalDate;
import java.util.Set;

import com.NeighborhoodNet.Nnet.entities.Neighborhood;
import com.NeighborhoodNet.Nnet.entities.Post;
import com.NeighborhoodNet.Nnet.entities.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Feed {

    private String postId;
    private String title;
    private String category;
    private String description;
    private String location;
    private LocalDate postedTime;
    private String neighborhoodName;
    private String contact;
    private String user_name;
    private Set<Review> reviews;
    
     public Feed(Neighborhood neighborhood, Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.description = post.getDescription();
        this.location = post.getLocation();
        this.postedTime = post.getTimeCreated();
        this.neighborhoodName = neighborhood.getName();
        this.contact = post.getContact();
        this.user_name = post.getUser_id().getUsername();
        this.reviews = post.getReviews();
    }
}
