package com.NeighborhoodNet.Nnet.dtos.requests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUser {
    
    private String username;
    private int zipCode;
    private String neighborhoodName;
    private String password;
    private String email;
    private LocalDate dateCreated;

}
