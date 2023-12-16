package com.example.coreapi.Events.OrderEvents;

import com.example.coreapi.DTOs.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductToOrderEvent {
    private String eventIdentifier;

    private String productId;

    private String orderId;
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String categoryName;


}
