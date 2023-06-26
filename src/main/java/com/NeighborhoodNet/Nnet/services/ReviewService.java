package com.NeighborhoodNet.Nnet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.NeighborhoodNet.Nnet.dtos.requests.ReviewBack;
import com.NeighborhoodNet.Nnet.dtos.requests.ReviewRequest;
import com.NeighborhoodNet.Nnet.dtos.responces.ReviewsResponse;
import com.NeighborhoodNet.Nnet.entities.Post;
import com.NeighborhoodNet.Nnet.entities.Review;
import com.NeighborhoodNet.Nnet.entities.User;
import com.NeighborhoodNet.Nnet.repositories.PostRepository;
import com.NeighborhoodNet.Nnet.repositories.ReviewRepository;
import com.NeighborhoodNet.Nnet.repositories.UserRepository;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.RoleNotFoundException;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.UserNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;


    public void createReview(ReviewRequest req, String user_id) {
        Optional<User> userOpt = userRepository.findById(user_id);

        User user = userOpt.get();

        Optional<Post> postOpt = postRepository.findById(req.getPostId());

        Post post = postOpt.get();
        
        Review review = new Review(req, user, post);

        reviewRepository.save(review);
    }


    public List<ReviewsResponse> getAll(String postId, String user_id) {

        
        Optional<User> userOpt = userService.findById(user_id);
        User user = userOpt.get();

        List<ReviewsResponse> reviewList = new ArrayList<>();

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User Not Found!");
        }

        Optional<Post> postOpt = postRepository.findById(postId);
    
        Post post = postOpt.get();

        List<Review> reviews = reviewRepository.findByPostId(post);

        for (Review review : reviews) {
            reviewList.add(new ReviewsResponse(user, review));
        }

        return reviewList;

        
    }


    public void addLike(ReviewBack req, String user_id) {

        Optional<User> userOpt = userService.findById(user_id);
        // User user = userOpt.get();

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User Not Found!");
        }

        Optional<Post> postOpt = postRepository.findById(req.getPostId());
    
        Post post = postOpt.get();

        List<Review> reviews = reviewRepository.findByPostId(post);

        Review review = reviews.get(0);

        review.addLike();
        reviewRepository.save(review);

    }


    public void deleteLike(ReviewBack req, String user_id) {
        
        Optional<User> userOpt = userService.findById(user_id);
        // User user = userOpt.get();

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User Not Found!");
        }

        Optional<Post> postOpt = postRepository.findById(req.getPostId());
    
        Post post = postOpt.get();

        List<Review> reviews = reviewRepository.findByPostId(post);

        Review review = reviews.get(0);

        if(!post.getUser_id().getId().equals(user_id)){
            throw new RoleNotFoundException("Can not delete this");
        }

        if(!review.getUser_id().getId().equals(user_id)){
            throw new RoleNotFoundException("Can not delete this");
        }
        
        reviewRepository.delete(review);
    }


    public void removeComment(String comment) {

        System.out.println(comment);
        System.out.println(comment);System.out.println(comment);System.out.println(comment);System.out.println(comment);System.out.println(comment);System.out.println(comment);System.out.println(comment);

         Optional<Review> reviewOpt = reviewRepository.findBycomment(comment);
        Review review = reviewOpt.get();

        if (reviewOpt.isEmpty()) {
            throw new UserNotFoundException("comment Not Found!");
        }

        reviewRepository.deleteById(review.getId());
    }
    
}
