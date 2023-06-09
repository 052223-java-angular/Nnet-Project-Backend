package com.NeighborhoodNet.Nnet.utils.custome_exceprions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException (String message){
        super(message);
    }
    
}
