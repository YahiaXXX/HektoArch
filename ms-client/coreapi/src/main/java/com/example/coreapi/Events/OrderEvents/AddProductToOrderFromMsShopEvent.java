package com.example.coreapi.Events.OrderEvents;

import com.example.coreapi.DTOs.OrderDTOshop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductToOrderFromMsShopEvent {
    private String eventIdentifier;
    private String productId;
    private OrderDTOshop order;

}


