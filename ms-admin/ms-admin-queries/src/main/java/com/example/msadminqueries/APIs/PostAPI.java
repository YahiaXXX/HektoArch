package com.example.msadminqueries.APIs;

import com.example.msadminqueries.DAOs.PostDAO;
import com.example.msadminqueries.Proxies.PostsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostAPI {
    @Autowired
    private PostsProxy postProxy;

    @GetMapping("/getAllPosts")
    public List<PostDAO> getAllPosts() {
        try {
            List<PostDAO> postDAOList=postProxy.getAllPosts();
            return postDAOList;
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    @GetMapping("/getPostById/{postId}")
    public PostDAO getPostById(@PathVariable String postId) {
        try {
            PostDAO postDAO=postProxy.getPostById(postId);
            return postDAO;
        }catch (Exception e){
            return new PostDAO();
        }
    }

    @GetMapping("/getPostByClientId/{clientId}")
    public List<PostDAO> getPostsByClientId(@PathVariable String clientId) {
        try {
            List<PostDAO> postDAOList=postProxy.getPostsByClientId(clientId);
            return postDAOList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
