package com.NeighborhoodNet.Nnet.entities;

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

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(name = "user_name", nullable = false)
    private String user_name;
    
    
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date_created")
    private String date_created;

    @Column(name = "zip_code", nullable = false)
    private String zip_code;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role_id;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    @JsonBackReference
    private Neighborhood neighborhood_id;

    

}

