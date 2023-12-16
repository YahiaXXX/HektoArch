package com.example.coreapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String productIdJoinColumn;


    private String productId;
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String categoryName;
}
