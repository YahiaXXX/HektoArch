package com.example.msadminqueries.Proxies;

import com.example.msadminqueries.DAOs.PostDAO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-client-queries")
@LoadBalancerClient(name = "ms-client-queries")
public interface PostsProxy {

    @GetMapping("/posts/getAllPosts")
    public List<PostDAO> getAllPosts();

    @GetMapping("/posts/getPostById/{postId}")
    public PostDAO getPostById(@PathVariable String postId);

    @GetMapping("/posts/getPostByClientId/{clientId}")
    public List<PostDAO> getPostsByClientId(@PathVariable String clientId) ;

}
