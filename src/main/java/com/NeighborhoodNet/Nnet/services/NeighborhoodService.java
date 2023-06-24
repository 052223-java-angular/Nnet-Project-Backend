package com.NeighborhoodNet.Nnet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.NeighborhoodNet.Nnet.dtos.requests.NewUserRequest;
import com.NeighborhoodNet.Nnet.dtos.requests.UpdateUser;
import com.NeighborhoodNet.Nnet.dtos.responces.AllHoods;
import com.NeighborhoodNet.Nnet.entities.Neighborhood;
import com.NeighborhoodNet.Nnet.repositories.NeighborhoodRepository;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

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

    public List<AllHoods> getAll() {
         List<Neighborhood> hoods = neighborhoodRepository.findAll();
        List<AllHoods> allhoods = new ArrayList<>();


        if (hoods.isEmpty()) {
            throw new UserNotFoundException("neighborhood Not Found!");
        }

       
        for (Neighborhood neighborhood : hoods) {
            allhoods.add(new AllHoods(neighborhood));
        }

            return allhoods;
    }
    
}
