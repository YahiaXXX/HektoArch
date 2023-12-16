package com.example.coreapis.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {


    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String productImageUrl;

    private String productDesc;

    private String categoryName;

}
