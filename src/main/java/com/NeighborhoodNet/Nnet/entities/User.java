package com.NeighborhoodNet.Nnet.entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

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
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(name = "username", nullable = false)
    private String username;
    
    
    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "zip_code", nullable = false)
    private int zipCode;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role_id;


    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    @JsonBackReference
    private Neighborhood neighborhood_id;


    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Post> posts;

    public User(String username, String password, Role role_id, int zipCode, Neighborhood neighborhood_id) {
    this.id = UUID.randomUUID().toString();
    this.username = username;
    this.password = password;
    this.dateCreated = LocalDate.now();
    this.role_id = role_id;
    this.zipCode = zipCode;
    this.neighborhood_id = neighborhood_id;
    }
    

}

