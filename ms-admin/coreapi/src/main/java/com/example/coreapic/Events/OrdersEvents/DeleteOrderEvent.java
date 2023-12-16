package com.example.coreapic.Events.OrdersEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderEvent {
    private String eventIdentifier;

    private String orderId;

}
