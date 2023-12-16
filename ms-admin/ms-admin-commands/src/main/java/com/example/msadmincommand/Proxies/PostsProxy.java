package com.example.msadmincommand.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-client-commands")
@LoadBalancerClient(name = "ms-client-commands")
public interface PostsProxy {

    @DeleteMapping("/posts/deleteByAdmin/{postId}")
    public String deletePostByPostIdByAdmin(@PathVariable("postId") String postId);


}
