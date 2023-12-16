package com.example.coreapis.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateDTO {
    private String productId;
    private String shopId;
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String productImageUrl;

    private String productDesc;

    private Double priceBefore;
    private String categoryName;

    private String orderId;
}
