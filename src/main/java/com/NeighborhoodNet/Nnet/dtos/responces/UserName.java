package com.NeighborhoodNet.Nnet.dtos.responces;

import com.NeighborhoodNet.Nnet.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserName {

    String username;
    String userRole;


    public UserName(User user) {
        this.username = user.getUsername();
        this.userRole = user.getRole_id().getName();
    }
    
}
