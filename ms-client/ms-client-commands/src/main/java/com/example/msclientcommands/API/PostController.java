package com.example.msclientcommands.API;

import com.example.coreapi.Commands.PostCommands.DeletePostCommand;
import com.example.coreapi.Commands.PostCommands.UpdatePostCommand;
import com.example.coreapi.DTOs.PostDTO;
import com.example.coreapi.DTOs.UserDTO;
import com.example.msclientcommands.Proxies.MsAuthProxy;
import com.example.msclientcommands.Proxies.PostProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import com.example.coreapi.Commands.PostCommand.CreatePostCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController

@RequestMapping("/posts")
public class PostController {

    @Autowired
    private MsAuthProxy msAuthProxy;

    private final CommandGateway commandGateway;

    @Autowired
    private PostProxy postProxy;

    public PostController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        if (postDTO.getPostTitle().isEmpty()) {
            return ResponseEntity.ok("Please give the New Post a Title.");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response conatins userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            System.out.println("before create post command");
            CreatePostCommand command = new CreatePostCommand(
                    postDTO.getPostTitle(),
                    postDTO.getPostDescription(),
                    postDTO.getPostImageUrl(),
                    extractedSubstringWithRange);
            // Send the command
            commandGateway.send(command);
            System.out.println("after create post command");

            return ResponseEntity.ok("Post creation command sent.");
        }
        return ResponseEntity.ok("Please login to continue");

    }


    //UPDATE
    @PatchMapping("/update/{postId}")
    public ResponseEntity<String> updatePost(@RequestBody PostDTO postDTO, @PathVariable String postId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        if (postDTO.getPostTitle().isEmpty()) {
            return ResponseEntity.ok("Please give the New Category a name.");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response conatins userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            if (postProxy.getPostById(postId).get().getPostTitle() != null) {
                UpdatePostCommand command = new UpdatePostCommand(
                        postId,
                        postDTO.getPostTitle(),
                        postDTO.getPostDescription(),
                        postDTO.getPostImageUrl(),
                        extractedSubstringWithRange);

                // Send the command
                commandGateway.send(command);

                return ResponseEntity.ok("Post Update command sent.");
            }
            return ResponseEntity.ok("Post does not exist.");
        }
        return ResponseEntity.ok("Please login to continue");

    }

    //DELETE
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        if (postProxy.getPostById(postId).get().getPostTitle() != null) {
            DeletePostCommand command = new DeletePostCommand(postId);

            // Send the command
            commandGateway.send(command);
            System.out.println("********** after send the Command **********");

            return ResponseEntity.ok("Post Delete command sent.");
        } else {
            return ResponseEntity.ok("Post does not exist.");
        }
    }
    @DeleteMapping("/deleteByAdmin/{postId}")
    public ResponseEntity<String> deletePostByAdmin(@PathVariable String postId) {


        if (postProxy.getPostById(postId).get().getPostTitle() != null) {
            DeletePostCommand command = new DeletePostCommand(postId);
            // Send the command
            commandGateway.send(command);
            System.out.println("********** after send the Command **********");

            return ResponseEntity.ok("Post Delete command sent.");
        } else {
            return ResponseEntity.ok("Post does not exist.");
        }
    }

}
