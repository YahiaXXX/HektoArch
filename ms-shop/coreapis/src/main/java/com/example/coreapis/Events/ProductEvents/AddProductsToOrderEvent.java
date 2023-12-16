package com.example.coreapis.Events.ProductEvents;

import com.example.coreapis.DTOs.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductsToOrderEvent {
    private String eventIdentifier;
    private List<String> productsIds;
    private List<Integer> productsQuantities;
    private OrderDTO order;

}


