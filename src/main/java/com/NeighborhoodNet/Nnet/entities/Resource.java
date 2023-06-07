package com.NeighborhoodNet.Nnet.entities;

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
@Table(name = "resources")
public class Resource {

    @Id
    private String id;

    @Column(nullable = false)
    private String resource_name;

    @Column(nullable = false)
    private String resource_category;
    
    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    @JsonBackReference
    private Neighborhood neighborhood_id;
    
}
