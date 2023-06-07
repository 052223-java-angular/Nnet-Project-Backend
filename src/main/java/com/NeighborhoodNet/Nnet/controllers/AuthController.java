package com.NeighborhoodNet.Nnet.controllers;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NeighborhoodNet.Nnet.dtos.requests.NewUserRequest;
import com.NeighborhoodNet.Nnet.services.UserService;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.ResourceConflictException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

   
    @PostMapping("/register")
    public ResponseEntity<?>resisterUser(@RequestBody NewUserRequest req){

        //check username is unique

        if(!userService.isUniqueUsername(req.getUser_name())){

            throw new ResourceConflictException("User name is not unique");

        }


        //check username validation


        //check password

        // validate password





        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }



    // @PostMapping("/login")






    /* Exception Handling section  */

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e){

        Map<String, Object> map = new HashMap<>();

        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
        
    }
    

}
