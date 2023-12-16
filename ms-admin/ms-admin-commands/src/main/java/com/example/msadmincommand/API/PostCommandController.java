package com.example.msadmincommand.API;

import com.example.msadmincommand.Proxies.PostsProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/posts")
public class PostCommandController {

    @Autowired
    private PostsProxy postProxy;

    private final CommandGateway commandGateway;

    public PostCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //DELETE
    @DeleteMapping("/deleteByAdmin/{postId}")
    public ResponseEntity<String> deletePostByAdmin(@PathVariable String postId,@CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if(token.equals("No token found in cookie")){
            return  ResponseEntity.ok("Please login to continue");
        }
        String s= postProxy.deletePostByPostIdByAdmin(postId);
        return ResponseEntity.ok(s);

    }
}
