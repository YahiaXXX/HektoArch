package com.example.coreapis.Events.ProductEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductEvent {
    private String eventIdentifier;
    private String productId;
    private String shopId;
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String productImageUrl;

    private String productDesc;

//    private Double priceBefore;
    private String categoryName;

    private String orderId;

}
