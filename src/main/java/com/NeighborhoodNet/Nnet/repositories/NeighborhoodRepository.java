package com.NeighborhoodNet.Nnet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NeighborhoodNet.Nnet.entities.Neighborhood;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, String>{

    Neighborhood findByzipCode(int zipCode);

    
}
