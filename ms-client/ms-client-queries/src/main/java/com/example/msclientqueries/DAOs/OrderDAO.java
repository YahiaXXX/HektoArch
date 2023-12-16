package com.example.msclientqueries.DAOs;

import com.example.coreapi.DTOs.OrderStatus;
import com.example.msclientqueries.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDAO {
    private String orderId;
    private Date ordeDate;
    private OrderStatus orderStatus;
   // private S tring shoppingCartId;
    private String clientId;
    private List<Product> products;
    private Double totalPrice;

}
