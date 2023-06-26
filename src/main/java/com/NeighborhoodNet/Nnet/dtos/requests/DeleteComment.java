package com.NeighborhoodNet.Nnet.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteComment {

    String userId;
    String usernaame;
    String comment;
    int like;

    
}
