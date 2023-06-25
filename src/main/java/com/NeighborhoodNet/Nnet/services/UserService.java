package com.NeighborhoodNet.Nnet.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.NeighborhoodNet.Nnet.dtos.requests.AdminUpdateUser;
import com.NeighborhoodNet.Nnet.dtos.requests.NewLoginRequest;
import com.NeighborhoodNet.Nnet.dtos.requests.NewUserRequest;
import com.NeighborhoodNet.Nnet.dtos.requests.UpdateUser;
import com.NeighborhoodNet.Nnet.dtos.responces.AllUsers;
import com.NeighborhoodNet.Nnet.dtos.responces.Principal;
import com.NeighborhoodNet.Nnet.dtos.responces.UpdateUserResponse;
import com.NeighborhoodNet.Nnet.dtos.responces.UserName;
import com.NeighborhoodNet.Nnet.entities.Neighborhood;
import com.NeighborhoodNet.Nnet.entities.Role;
import com.NeighborhoodNet.Nnet.entities.User;
import com.NeighborhoodNet.Nnet.repositories.NeighborhoodRepository;
import com.NeighborhoodNet.Nnet.repositories.UserRepository;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UserService {

    private final RoleService roleService;
    private final NeighborhoodService neighborhoodService;
    private final UserRepository userRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    
    

    public boolean isUniqueUsername(String username){

        Optional<User> userOptional = userRepository.findByusername(username);

        return userOptional.isEmpty();
    }

      public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public boolean isValidZipCode(int zipCode) {  
        String zipcode = Integer.toString(zipCode);
        return zipcode.matches("^\\d{5}$");
    
    }

    public boolean isValidId(String userId) {
         Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.isEmpty();
    }

   public User registerUser(NewUserRequest req) {

        // find role USER
        Role foundRole = roleService.findByname("USER");


        /* this method will find if this zipcode exist in the neighborhood table
            if it exist it will return neighborhood_id and update the census by ++1 else it will call save method to create new neighborhood
        */
        Neighborhood neighborhood = neighborhoodService.findByzipCode(req.getZipCode());

        if(neighborhood == null){

            neighborhood = neighborhoodService.save(req);
        } else {
           neighborhood = neighborhoodService.updateByzipCode(req.getZipCode());
        }

        // hash password
        String hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());

        // create new user
        User newUser = new User(req.getUsername(), hashed, foundRole, req.getZipCode(), neighborhood);

        // save and return user
        return userRepository.save(newUser);
    }


    public Principal login(NewLoginRequest req){
        Optional<User> userOpt = userRepository.findByusername(req.getUsername());

        if(userOpt.isPresent()){
            User foundUser = userOpt.get();
            if(BCrypt.checkpw(req.getPassword(), foundUser.getPassword())){

                return new Principal(foundUser);

            }
        }
        
        throw new UserNotFoundException("Invalid username or password");


    }

    public Optional<User> findById(String user_id) {
        return userRepository.findById(user_id);
    }

    public List<AllUsers> getAll() {

        List<User> users = userRepository.findAll();
        List<AllUsers> allUsers = new ArrayList<>();


        if (users.isEmpty()) {
            throw new UserNotFoundException("User Not Found!");
        }

       
        for (User user : users) {
            allUsers.add(new AllUsers(user));
        }

            return allUsers;
        }


    public UpdateUserResponse getUserProfile(String user_id) {
        
        Optional<User> userOpt = userRepository.findById(user_id);
        User user = userOpt.get();


        if (user.equals(null)) {
            throw new UserNotFoundException("User Not Found!");
        }


        UpdateUserResponse info = new UpdateUserResponse(user);

        return info;
    }

    public void updateUser(UpdateUser req, String user_id) {

        Optional<User> userOpt = userRepository.findById(user_id);
        User user = userOpt.get();


        if (user.equals(null)) {
            throw new UserNotFoundException("User Not Found!");
        }

        Neighborhood neighborhood = neighborhoodService.saveNew(req);

        String hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());


        user.setEmail(req.getEmail());
        user.setNeighborhoodId(neighborhood);
        user.setPassword(hashed);
        user.setUsername(req.getUsername());
        user.setZipCode(req.getZipCode());

        userRepository.save(user);

    }

    public void removerUser(String userId) {

        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.get();

        Neighborhood neighborhood = user.getNeighborhoodId();

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User Not Found!");
        }

        userRepository.delete(user);

        neighborhood.decreaseCensus();

        neighborhoodRepository.save(neighborhood);


    }

    public void updateUser(AdminUpdateUser req) {
        
        Optional<User> userOpt = userRepository.findById(req.getUserId());
        User user = userOpt.get();


        if (user.equals(null)) {
            throw new UserNotFoundException("User Not Found!");
        }

        Role foundRole = roleService.findByname("ADMIN");
        user.setRole_id(foundRole);

        userRepository.save(user);
    }

    public UserName getUserName(String user_id) {

        Optional<User> userOpt = userRepository.findById(user_id);
        User user = userOpt.get();


        if (user.equals(null)) {
            throw new UserNotFoundException("User Not Found!");
        }

        UserName info = new UserName(user);

        return info;

    }

  

    


}
