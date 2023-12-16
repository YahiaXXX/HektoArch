package com.example.coreapis.Commands.ProductCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductCommand {
    private String productId;
    private String shopId;

    private String productName;

    private Double productPrice;

    private Integer productQuantity;
    private String productImageUrl;

    private String productDesc;

    private String categoryName;

}
