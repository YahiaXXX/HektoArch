package com.example.coreapi.Events;

import com.example.coreapi.DTOs.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusEvent {

    private String eventIdentifier;
    private String orderId;
    private OrderStatus orderStatus;
}
