package com.example.coreapi.Commands.OrderCommands;

import com.example.coreapi.DTOs.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@XStreamAlias("AddProductToOrderCommand")
public class AddProductsToOrderCommand {

    private List<ProductDTO> productDTOList;
    private String orderId;
}
