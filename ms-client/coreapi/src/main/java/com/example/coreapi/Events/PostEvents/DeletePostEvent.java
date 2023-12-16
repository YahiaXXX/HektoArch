package com.example.coreapi.Events.PostEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletePostEvent {
    private String eventIdentifier;

    private String postId;

}
