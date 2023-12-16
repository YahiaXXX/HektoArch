package com.example.coreapi.Events.OrderEvents;

import com.example.coreapi.DTOs.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductsToOrderEvent {
    private String eventIdentifier;

    private String orderId;


    private List<ProductDTO> productDTOList;

}
