package com.NeighborhoodNet.Nnet.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.NeighborhoodNet.Nnet.entities.User;
import com.NeighborhoodNet.Nnet.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    

    public boolean isUniqueUsername(String user_name){

        Optional<User> userOptional = userRepository.findByUsername(user_name);

        return userOptional.isEmpty();
    }
}
