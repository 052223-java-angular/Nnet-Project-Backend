package com.NeighborhoodNet.Nnet.entities;

import java.util.UUID;

import com.NeighborhoodNet.Nnet.dtos.requests.ReviewRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    private String id;
    
    @Column(nullable = false)
    private int rating;

    @Column
    private String comment;

    @Column
    private int likes;

    @Column
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonBackReference
    private Post postId;


    public Review(ReviewRequest req, User user, Post post) {
        this.id= UUID.randomUUID().toString();
        this.comment = req.getComment();
        this.likes = 1;
        this.user_id = user;
        this.username = user.getUsername();
        this.postId = post;
    }


    public void addLike() {
        likes++;
    }


}
