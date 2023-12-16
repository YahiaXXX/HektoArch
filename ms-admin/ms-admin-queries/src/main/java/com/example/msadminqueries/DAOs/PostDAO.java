package com.example.msadminqueries.DAOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDAO {
    private String postId;
    private String postTitle;
    private String postDescription;
    private String postImageUrl;
    private String clientId;
}
