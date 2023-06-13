package com.NeighborhoodNet.Nnet.utils.custome_exceprions;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException(String message){
        super(message);
    }
    
}
