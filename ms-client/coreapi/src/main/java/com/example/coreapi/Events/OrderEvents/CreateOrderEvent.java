package com.example.coreapi.Events.OrderEvents;

import com.example.coreapi.DTOs.OrderStatus;
import com.example.coreapi.DTOs.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderEvent {
    private String eventIdentifier;

    private String orderId;
    private Date orderDate;
    private OrderStatus orderStatus;
  //  private String shoppingCartId;
    private String clientId;
    private List<ProductDTO> products;


}
