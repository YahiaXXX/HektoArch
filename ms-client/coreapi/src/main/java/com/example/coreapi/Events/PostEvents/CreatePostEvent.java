package com.example.coreapi.Events.PostEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostEvent {
    private String eventIdentifier;

    private String postId;
    private String postTitle;
    private String postDescription;
    private String postImageUrl;
    private String clientId;
}
