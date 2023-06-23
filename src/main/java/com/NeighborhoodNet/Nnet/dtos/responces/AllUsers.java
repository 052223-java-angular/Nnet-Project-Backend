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
public class AllUsers {

    
    private String id;
    private String username;
    private String role_id;
    private String email;
    private LocalDate dateCreated;
    private String neighborhoodName;
    private String neighborhoodId; //might screem if this is not neibourhood object



    public AllUsers(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.role_id = user.getRole_id().getId();
        this.email = user.getEmail();
        this.dateCreated = user.getDateCreated();
        this.neighborhoodName = user.getNeighborhoodId().getName();
        this.neighborhoodId = user.getNeighborhoodId().getId();
        
    }
    
}
