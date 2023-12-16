package com.example.coreapi.Commands.PostCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletePostCommand {

    private String postId;
}
