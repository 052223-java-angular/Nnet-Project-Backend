package com.NeighborhoodNet.Nnet.entities;

import java.util.Set;

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
@Table(name = "events")
public class Event {

    @Id
    private String id;
    
    @Column(nullable = false)
    private String event_title;
    
    @Column(nullable = false)
    private String category_id;

    
    @Column(nullable = false)
    private String event_description;

    
    @Column(nullable = false)
    private String event_date;

    
    @Column(nullable = false)
    private String event_contact;

    
    @Column(nullable = false)
    private String event_location;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Review> review;

    // @Column(nullable = false)
    // private String resource_id;

    
}
