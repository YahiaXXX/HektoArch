package com.example.coreapic.Events.PostEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePostEvent {
    private String eventIdentifier;

    private String productId;

}
