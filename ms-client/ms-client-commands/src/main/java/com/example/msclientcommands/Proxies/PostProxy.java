package com.example.msclientcommands.Proxies;

import com.example.coreapi.DTOs.OrderDTO;
import com.example.coreapi.DTOs.PostDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-client-queries")
@LoadBalancerClient(name = "ms-client-queries")

public interface PostProxy {

    @GetMapping("/posts/getPostById/{postId}")
    public Optional<PostDTO> getPostById(@PathVariable("postId") String postId);

}
