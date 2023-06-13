package com.NeighborhoodNet.Nnet.dtos.responces;

import java.time.LocalDate;

import com.NeighborhoodNet.Nnet.entities.Neighborhood;
import com.NeighborhoodNet.Nnet.entities.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Feed {

    private String title;
    private String category;
    private String description;
    private String location;
    private LocalDate postedTime;
    private String neighborhood_name;
    private String contact;
    private String user_name;
    
     public Feed(Neighborhood neighborhood, Post posts) {

        this.title = posts.getTitle();
        this.category = posts.getCategory();
        this.description = posts.getDescription();
        this.location = posts.getLocation();
        this.postedTime = posts.getTimeCreated();
        this.neighborhood_name = neighborhood.getName();
        this.contact = posts.getContact();
        this.user_name = posts.getUser_id().getUsername();
        
    }
}
