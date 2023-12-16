package com.example.msclientqueries.Projections;

import com.example.coreapi.Events.PostEvents.CreatePostEvent;
import com.example.coreapi.Events.PostEvents.DeletePostEvent;
import com.example.coreapi.Events.PostEvents.UpdatePostEvent;
import com.example.msclientqueries.Entities.PostEntity;
import com.example.msclientqueries.Services.PostService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class PostProjection {

    @Autowired
    public PostService postService;

    @EventHandler
    public void createPostEventHandler(CreatePostEvent event) {
        System.out.println("********** after send the creation Command and after send the Event **********");
        PostEntity post = new PostEntity(
                event.getPostId(),
                event.getPostTitle(),
                event.getPostDescription(),
                event.getPostImageUrl(),
                event.getClientId()
        );
        postService.addPost(post);
    }

    @EventHandler
    public void updatePostEventHandler(UpdatePostEvent event) {
        System.out.println("********** after send the UPDATE Command and after send the Event **********");
        PostEntity post = new PostEntity(
                event.getPostId(),
                event.getPostTitle(),
                event.getPostDescription(),
                event.getPostImageUrl(),
                event.getClientId()
        );
        postService.updatePost(post);
    }

    @EventHandler
    public void deletePostEventHandler(DeletePostEvent event) {
        System.out.println("********** after send the delete Command and after send the Event **********");
        postService.deletePost(event.getPostId());

    }
}
