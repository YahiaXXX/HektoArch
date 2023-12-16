package com.example.coreapis.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String orderId;
    private Date ordeDate;
    private OrderStatus orderStatus;
    // private String shoppingCartId;
    private String clientId;
    private Double totalPrice;


}
