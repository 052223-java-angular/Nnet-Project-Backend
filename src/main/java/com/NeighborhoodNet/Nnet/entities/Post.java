package com.NeighborhoodNet.Nnet.entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JoinColumn(name = "neighborhood_id")
    @JsonBackReference
    private Neighborhood neighborhood_id;

    //user_id
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user_id;

    //review_id

    @OneToMany(mappedBy = "post_id", fetch = FetchType.LAZY )
    @JsonManagedReference
    private Set<Review> reviews;





    
}
