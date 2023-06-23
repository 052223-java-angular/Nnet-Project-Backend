package com.NeighborhoodNet.Nnet.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NeighborhoodNet.Nnet.dtos.requests.AdminUpdateUser;
import com.NeighborhoodNet.Nnet.dtos.responces.AllUsers;
import com.NeighborhoodNet.Nnet.services.JwtTokenService;
import com.NeighborhoodNet.Nnet.services.PostService;
import com.NeighborhoodNet.Nnet.services.UserService;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.ResourceConflictException;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.RoleNotFoundException;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final JwtTokenService tokenService;
    private final UserService userService;
    private final PostService postService;

     @GetMapping("/users")
    public ResponseEntity<List<AllUsers>> feedUsers(HttpServletRequest sreq){

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

    
     @DeleteMapping("/deleteuser")
     public ResponseEntity<?>deleteUser(@RequestBody AdminUpdateUser req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }
        String admin_id = tokenService.extractUserId(token);

        //check if the user id exist
        if(userService.isValidId(req.getUserId())){

            throw new ResourceConflictException("Can not find User!");

        }

        if(!tokenService.extractUserRole(token).equals("ADMIN")){
            throw new RoleNotFoundException("Unauthorized Access!!!");
        }

          //check if the user to be deleted is an own account and prevent self delition for ADMIN account only
        if(admin_id.equals(req.getUserId())){
            throw new ResourceConflictException("Can't Delete Own Account");
        }


        userService.removerUser(req.getUserId());

    
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    
    }


    @PutMapping("/update")
    public ResponseEntity<?>updateUserRole(@RequestBody AdminUpdateUser req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }

        //check if the user id exist
        if(userService.isValidId(req.getUserId())){

            throw new ResourceConflictException("Can not find User!");

        }

        if(!tokenService.extractUserRole(token).equals("ADMIN")){
            throw new RoleNotFoundException("Unauthorized Access!!!");
        }


        
        // update user
        userService.updateUser(req);

        // return Accepted if updated
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        
    }

    @DeleteMapping("/deletePost")
     public ResponseEntity<?>deletePost(@RequestBody AdminUpdateUser req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }

        //check if the user id exist
        if(postService.isValidId(req.getPostId())){

            throw new ResourceConflictException("Can not find Post!");

        }

        if(!tokenService.extractUserRole(token).equals("ADMIN")){
            throw new RoleNotFoundException("Unauthorized Access!!!");
        }


        postService.removePost(req.getPostId());

    
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    
    }

    @DeleteMapping("/deletecomment")
     public ResponseEntity<?>deletecomment(@RequestBody AdminUpdateUser req, HttpServletRequest sreq){

        String token = sreq.getHeader("auth-token");

        boolean bool = tokenService.isTokenExpired(token);
        
        if(token == null || bool == true){
            throw new UserNotFoundException("Invalid user");
        }

        //check if the user id exist
        if(postService.isValidId(req.getPostId())){

            throw new ResourceConflictException("Can not find Post!");

        }

        if(!tokenService.extractUserRole(token).equals("ADMIN")){
            throw new RoleNotFoundException("Unauthorized Access!!!");
        }


        postService.removePost(req.getPostId());

    
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    
    }

    
}
