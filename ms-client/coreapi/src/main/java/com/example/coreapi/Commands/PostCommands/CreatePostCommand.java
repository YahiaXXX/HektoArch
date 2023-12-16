package com.example.coreapi.Commands.PostCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostCommand {
    private String postTitle;
    private String postDescription;
    private String postImageUrl;
    private String clientId;
}

