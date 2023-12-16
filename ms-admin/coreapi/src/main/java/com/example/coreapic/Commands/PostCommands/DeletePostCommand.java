package com.example.coreapic.Commands.PostCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePostCommand {
    private String postId;

}
