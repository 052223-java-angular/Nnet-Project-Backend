package com.NeighborhoodNet.Nnet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NeighborhoodNet.Nnet.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String>{

    // Post findByneighborhood_id(String id);


    
}
