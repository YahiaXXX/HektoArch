package com.example.msclientqueries.APIs;

import com.example.msclientqueries.DAOs.PostDAO;
import com.example.msclientqueries.Services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    public final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping("/getAllPosts")
    public List<PostDAO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/getPostById/{postId}")
    public PostDAO getPostById(@PathVariable String postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/getPostByClientId/{clientId}")
    public List<PostDAO> getPostsByClientId(@PathVariable String clientId) {
        return postService.findPostEntitiesByClientId(clientId);
    }

}
