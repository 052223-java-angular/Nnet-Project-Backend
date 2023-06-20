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

        // register user
        userService.updateUser(req, user_id);

        // return 201 - CREATED
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        
    }


    
}
