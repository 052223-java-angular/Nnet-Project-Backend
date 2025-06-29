package com.NeighborhoodNet.Nnet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NeighborhoodNet.Nnet.dtos.requests.NewLoginRequest;
import com.NeighborhoodNet.Nnet.dtos.requests.NewUserRequest;
import com.NeighborhoodNet.Nnet.dtos.responces.Principal;
import com.NeighborhoodNet.Nnet.services.JwtTokenService;
import com.NeighborhoodNet.Nnet.services.UserService;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.ResourceConflictException;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenService tokenService;

   
    @PostMapping("/register")
    public ResponseEntity<?>resisterUser(@RequestBody NewUserRequest req){

        //check username is unique

        if(!userService.isUniqueUsername(req.getUsername())){

            throw new ResourceConflictException("User name is not unique");

        }

         if (!userService.isValidUsername(req.getUsername())) {
            throw new ResourceConflictException(
                    "Username needs to be 8-20 characters long and can only contain letters, numbers, periods, and underscores");
        }

        // if username is not unique, throw exception
        if (!userService.isUniqueUsername(req.getUsername())) {
            throw new ResourceConflictException("Username is not unique");
        }

        // if password is not valid, throw exception
        if (!userService.isValidPassword(req.getPassword())) {
            throw new ResourceConflictException("Password needs to be at least 8 characters long and contain at least one letter and one number");
        }

        // if password and confirm password do not match, throw exception
        if (!userService.isSamePassword(req.getPassword(), req.getConfirmPassword())) {
            throw new ResourceConflictException("Passwords do not match");
        }

        //check if the zipcode is only 5 digits
        if (!userService.isValidZipCode(req.getZipCode())) {
             throw new ResourceConflictException("Zipcode is not Valid please enter only 5 digit number");
        }

        // register user
        userService.registerUser(req);

        // return 201 - CREATED
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }


    @PostMapping("/login")
    public ResponseEntity<Principal> login(@RequestBody NewLoginRequest req){

       Principal principal = userService.login(req);

       String token = tokenService.generateToken(principal);

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }

       principal.setToken(token);

       return ResponseEntity.status(HttpStatus.OK).body(principal);
        
    }
    

}
