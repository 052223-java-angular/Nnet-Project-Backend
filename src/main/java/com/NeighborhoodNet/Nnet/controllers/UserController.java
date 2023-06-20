package com.NeighborhoodNet.Nnet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NeighborhoodNet.Nnet.dtos.requests.UpdateUser;
import com.NeighborhoodNet.Nnet.dtos.responces.UpdateUserResponse;
import com.NeighborhoodNet.Nnet.services.JwtTokenService;
import com.NeighborhoodNet.Nnet.services.UserService;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.ResourceConflictException;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenService tokenService;

    @GetMapping("/profile")
    public ResponseEntity<UpdateUserResponse> getProfile(HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }

        String user_id = tokenService.extractUserId(token);

        UpdateUserResponse info = userService.getUserProfile(user_id);

        return ResponseEntity.status(HttpStatus.OK).body(info);
        
    }


    @PutMapping("/update")
    public ResponseEntity<?>updateUserProfile(@RequestBody UpdateUser req, HttpServletRequest sreq){

        //check username is unique

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        
        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        String user_id = tokenService.extractUserId(token);

        //perform validation on input before updating 
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

        //check if the zipcode is only 5 digits
        if (!userService.isValidZipCode(req.getZipCode())) {
             throw new ResourceConflictException("Zipcode is not Valid please enter only 5 digit number");
        }

        // update user
        userService.updateUser(req, user_id);

        // return Accepted if updated
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        
    }


    
}
