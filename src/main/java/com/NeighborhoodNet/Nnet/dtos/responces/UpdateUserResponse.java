package com.NeighborhoodNet.Nnet.dtos.responces;

import java.time.LocalDate;

import com.NeighborhoodNet.Nnet.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserResponse {
  
    private String id;
    private String username;
    private String role;
    private String email;
    private LocalDate dateCreated;
    private String neighborhoodName;
    private int zipcode;
    private String password;


      
    public UpdateUserResponse(User user) {
        
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole_id().getName();
        this.email = user.getEmail();
        this.dateCreated = user.getDateCreated();
        this.neighborhoodName = user.getNeighborhoodId().getName();
        this.zipcode = user.getZipCode();

    }



    // public UpdateUserResponse(UpdateUser req, User user) {
    //     this.username = req.getUsername();
    //     this.email = req.getEmail();
    //     this.neighborhoodName = req.getNeighborhoodName();
    //     this.zipcode = req.getZipCode();
    //     this.password = req.getPassword();
    // }

}
