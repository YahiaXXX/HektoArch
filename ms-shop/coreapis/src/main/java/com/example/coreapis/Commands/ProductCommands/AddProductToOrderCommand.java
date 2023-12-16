package com.example.coreapis.Commands.ProductCommands;

import com.example.coreapis.DTOs.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductToOrderCommand {

    private String productId;
    private Integer productQuantity;
    private OrderDTO order;
}
