package com.NeighborhoodNet.Nnet.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewPostRequest {

    private String title;
    private String category;
    private String description;
    private String location;
    private String contact;

}
