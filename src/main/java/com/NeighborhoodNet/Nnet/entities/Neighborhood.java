package com.NeighborhoodNet.Nnet.entities;

import java.util.Set;
import java.util.UUID;

import com.NeighborhoodNet.Nnet.dtos.requests.UpdateUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "neighborhoods")
public class Neighborhood {

    @Id
    private String id;
    
    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private int zipCode;

    @Column
    private int census;

    @Column
    private String country = "USA";


    @OneToMany(mappedBy = "neighborhoodId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> user;

    @OneToMany(mappedBy = "neighborhoodId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Post> post;


    public Neighborhood(int zipCode, String name) {
        this.id =  UUID.randomUUID().toString();
        this.name = name;
        this.zipCode = zipCode;
        this.census = 1;

    }

    public Neighborhood(UpdateUser req) {
        this.id =  UUID.randomUUID().toString();
        this.name = req.getNeighborhoodName();
        this.zipCode = req.getZipCode();
        this.census = 1;
    }


    public void increaseCensus(){
        census++;
    }


    
}
