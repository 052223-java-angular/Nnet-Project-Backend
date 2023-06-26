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


    public List<Feed> getAll(String user_id) {
    Optional<User> userOpt = userService.findById(user_id);
    List<Feed> feeds = new ArrayList<>();

    if (userOpt.isEmpty()) {
        throw new UserNotFoundException("User Not Found!");
    }

    Neighborhood neighborhood = userOpt.get().getNeighborhoodId();

    List<Post> posts = postRepository.findByneighborhoodId(neighborhood);

    for (Post post : posts) {
        feeds.add(new Feed(neighborhood, post));
    }

    return feeds;
}


    public void removePost(String postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        Post post = postOpt.get();

        if (postOpt.isEmpty()) {
            throw new UserNotFoundException("Post Not Found!");
        }

        postRepository.delete(post);

    }


    public boolean isValidId(String postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        return postOptional.isEmpty();
    }


    public List<Feed> getFiltered(String category, String user_id) {
        Optional<User> userOpt = userService.findById(user_id);
        List<Feed> feeds = new ArrayList<>();

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User Not Found!");
        }

        Neighborhood neighborhood = userOpt.get().getNeighborhoodId();

        List<Post> posts = postRepository.findByneighborhoodId(neighborhood);

        


        for (Post post : posts) {

            if(post.getCategory().equals(category)){

                feeds.add(new Feed(neighborhood, post));
            }            
        }

        return feeds;
    }


    
}
