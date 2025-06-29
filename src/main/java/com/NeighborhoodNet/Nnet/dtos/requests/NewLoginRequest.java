package com.NeighborhoodNet.Nnet.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewLoginRequest {
    
    private String username;
    private String password;
}
