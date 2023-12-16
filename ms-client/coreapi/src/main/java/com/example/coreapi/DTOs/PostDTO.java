package com.example.coreapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private String postId;
    private String postTitle;
    private String postDescription;
    private String postImageUrl;

}
