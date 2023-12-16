package com.example.coreapis.Commands.ProductCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddQuantityToProductCommand
{

    private String productId;
    private Integer productQuantity;
}
