package com.NeighborhoodNet.Nnet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NeighborhoodNet.Nnet.entities.Neighborhood;
import com.NeighborhoodNet.Nnet.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String>{

    List<Post> findByneighborhoodId(Neighborhood neighborhood_id);
    


    
}
