package com.example.coreapi.Events.OrderEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveProductFromOrderEvent {

    private String eventIdentifier;
    private String productIdJoinColumn;
    private String orderId;
}
