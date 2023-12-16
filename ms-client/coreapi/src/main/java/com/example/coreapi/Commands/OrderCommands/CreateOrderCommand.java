package com.example.coreapi.Commands.OrderCommands;

import com.example.coreapi.DTOs.OrderStatus;
import com.example.coreapi.DTOs.ProductDTO;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@XStreamAlias("CreateOrderCommand")
public class CreateOrderCommand {
    private Date orderDate;
    private OrderStatus orderStatus;
    //private String shoppingCartId;
    private String clientId;
    private List<ProductDTO> products;

}
