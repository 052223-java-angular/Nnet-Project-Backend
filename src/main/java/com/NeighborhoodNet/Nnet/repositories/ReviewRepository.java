package com.NeighborhoodNet.Nnet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NeighborhoodNet.Nnet.entities.Post;
import com.NeighborhoodNet.Nnet.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findByPostId(Post postId);

    Optional<Review> findBycomment(String comment);
    
}
