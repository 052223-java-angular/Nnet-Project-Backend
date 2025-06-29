package com.NeighborhoodNet.Nnet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NeighborhoodNet.Nnet.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByusername(String username);
    

}
