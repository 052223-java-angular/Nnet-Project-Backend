package com.NeighborhoodNet.Nnet.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "roles")
public class Role {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;


    @OneToMany(mappedBy = "role_id", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // cascade = CascadeType.ALL -> protects records from being orphaned when this table is deleted
    @JsonManagedReference
    private Set<User> users;

    
}
