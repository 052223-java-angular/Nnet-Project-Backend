package com.NeighborhoodNet.Nnet.dtos.responces;

import com.NeighborhoodNet.Nnet.entities.Neighborhood;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllHoods {

    String neighborhoodId;
    String NeighborhoodName;
    int zipCode;
    String country;
    int census;

     public AllHoods(Neighborhood neighborhood) {

        this.NeighborhoodName = neighborhood.getName();
        this.census = neighborhood.getCensus();
        this.country = neighborhood.getCountry();
        this.zipCode = neighborhood.getZipCode();
        this.neighborhoodId = neighborhood.getId();
        
    }
}
