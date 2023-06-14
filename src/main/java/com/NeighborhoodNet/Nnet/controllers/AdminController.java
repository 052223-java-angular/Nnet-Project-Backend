package com.NeighborhoodNet.Nnet.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NeighborhoodNet.Nnet.dtos.responces.AllUsers;
import com.NeighborhoodNet.Nnet.services.JwtTokenService;
import com.NeighborhoodNet.Nnet.services.UserService;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.RoleNotFoundException;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final JwtTokenService tokenService;
    private final UserService userService;

     @GetMapping("/users")
    public ResponseEntity<List<AllUsers>> feedPosts(HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        //extract the role to check if the user is ADMIN else deny access
        if(!tokenService.extractUserRole(token).equals("ADMIN")){
            throw new RoleNotFoundException("Unauthorized Access!!!");
        }

            List<AllUsers> users =  userService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
        
    }


    

     @PutMapping("/edit")
     public ResponseEntity<?>createPost(HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }

       if(!tokenService.extractUserRole(token).equals("ADMIN")){
            throw new RoleNotFoundException("Unauthorized Access!!!");
        }


        //using the Jwt userId extractor mathod user_Id will be extracted from the token

        // String user_id = tokenService.extractUserId(token);

        // postService.createPost(user_id);
     
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
    
    }

    
}
