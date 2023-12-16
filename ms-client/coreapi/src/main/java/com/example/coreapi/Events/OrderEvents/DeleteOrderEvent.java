package com.example.coreapi.Events.OrderEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderEvent {
    private String eventIdentifier;
    private String orderId;

}
