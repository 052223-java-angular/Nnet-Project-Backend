package com.NeighborhoodNet.Nnet.services;

import org.springframework.stereotype.Service;

import com.NeighborhoodNet.Nnet.dtos.requests.NewUserRequest;
import com.NeighborhoodNet.Nnet.dtos.requests.UpdateUser;
import com.NeighborhoodNet.Nnet.entities.Neighborhood;
import com.NeighborhoodNet.Nnet.repositories.NeighborhoodRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;


    public Neighborhood updateByzipCode(int zipCode) {

        Neighborhood neighbourhood = neighborhoodRepository.findByzipCode(zipCode);

        // if existing neighborhood found update the neighourhood census
        if (neighbourhood != null) {

            neighbourhood.increaseCensus();

            neighborhoodRepository.save(neighbourhood); // Save the updated neighborhood back to the database
        }

        return neighbourhood;
    }

    public Neighborhood save(NewUserRequest req) {

        Neighborhood neighborhood = new Neighborhood(req.getZipCode(), req.getNeighborhoodName());

        return neighborhoodRepository.save(neighborhood);

    }

    public Neighborhood findByzipCode(int zipCode) {
        return neighborhoodRepository.findByzipCode(zipCode);
    }

    public Neighborhood saveNew(UpdateUser req) {

         Neighborhood neighbourhood = neighborhoodRepository.findByzipCode(req.getZipCode());

        // if existing neighborhood found update the neighourhood census
        if (neighbourhood != null) {

            neighbourhood.increaseCensus();

            neighborhoodRepository.save(neighbourhood); // Save the updated neighborhood back to the database
            return neighbourhood;
    
        }
        else{
            Neighborhood newNeighborhood = new Neighborhood(req);
            neighborhoodRepository.save(newNeighborhood);
            return newNeighborhood;
        }        
    }
    
}
