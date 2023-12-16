package com.example.coreapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOshop {
    private String orderId;
    private Date ordeDate;
    private OrderStatus orderStatus;
    // private String shoppingCartId;
    private String clientId;
    private Double totalPrice;


}
