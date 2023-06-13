package com.NeighborhoodNet.Nnet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.NeighborhoodNet.Nnet.dtos.requests.NewPostRequest;
import com.NeighborhoodNet.Nnet.dtos.responces.Feed;
import com.NeighborhoodNet.Nnet.entities.Neighborhood;
import com.NeighborhoodNet.Nnet.entities.Post;
import com.NeighborhoodNet.Nnet.entities.User;
import com.NeighborhoodNet.Nnet.repositories.PostRepository;
import com.NeighborhoodNet.Nnet.repositories.UserRepository;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public void createPost(NewPostRequest req, String user_id) {

        Optional<User> userOpt = userRepository.findById(user_id);

        User user = userOpt.get();

        Post post = new Post(req, user);

        postRepository.save(post);
    }


    // public List<Feed> getAll(String user_id) {

        
    //     Optional<User> useropt =  userService.findById(user_id);
    //     List<Feed> feeds = new ArrayList<>();

    //     if(useropt.isEmpty()){
    //         throw new UserNotFoundException("User Not Found!");
    //     }

    //     User user = useropt.get();

    //     Neighborhood neighborhood = user.getNeighborhood_id();

    //     Post posts = postRepository.findByneighborhood_id(neighborhood.getId());

    //     while(!posts.getId().isEmpty()){

    //         feeds.add(new Feed(neighborhood, posts));

    //     }

    //     return feeds;
    // }

    
}
