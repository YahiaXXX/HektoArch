package com.example.coreapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Date orderDate;
    private OrderStatus orderStatus;
//  private String clientId;

  //  private String shoppingCartId;
  private List<ProductDTO> products;

}
