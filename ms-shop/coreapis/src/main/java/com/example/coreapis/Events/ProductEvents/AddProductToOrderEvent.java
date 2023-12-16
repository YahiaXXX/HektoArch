package com.example.coreapis.Events.ProductEvents;

import com.example.coreapis.DTOs.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductToOrderEvent {
    private String eventIdentifier;
    private String productId;
    private Integer productQuantity;
    private OrderDTO order;

}


