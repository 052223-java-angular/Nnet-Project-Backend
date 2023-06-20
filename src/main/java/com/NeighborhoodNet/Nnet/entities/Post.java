package com.NeighborhoodNet.Nnet.entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.NeighborhoodNet.Nnet.dtos.requests.NewPostRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "posts")
public class Post {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(name = "time_created")
    private LocalDate timeCreated;

    @Column
    private String description;

    private String location;

    private String contact;

    //neighbourhood_id
    @ManyToOne
    @JoinColumn(name = "neighborhoodId")
    @JsonBackReference
    private Neighborhood neighborhoodId;

    //user_id
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user_id;

    //review_id

    @OneToMany(mappedBy = "postId", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
    @JsonManagedReference
    private Set<Review> reviews;

    

    public Post(NewPostRequest req, User user) {

        this.id = UUID.randomUUID().toString();
        this.user_id = user;
        this.title = req.getTitle();
        this.contact = req.getContact();
        this.category = req.getCategory();
        this.description = req.getDescription();
        this.location = req.getLocation();
        this.neighborhoodId = user.getNeighborhoodId();
        this.timeCreated = LocalDate.now();

    }





    
}
