package com.example.coreapis.Commands.ProductCommands;

import com.example.coreapis.DTOs.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductsToOrderCommand {

    private List<String> productsIds;
    private List<Integer> productsQuantities;

    private OrderDTO order;
}
