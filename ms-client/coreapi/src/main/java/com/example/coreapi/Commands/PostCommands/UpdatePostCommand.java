package com.example.coreapi.Commands.PostCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostCommand {
    private String postId;
    private String postTitle;
    private String postDescription;
    private String postImageUrl;
    private String clientId;
}
