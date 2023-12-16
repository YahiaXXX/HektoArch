package com.example.coreapis.Events.ProductEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddQuantityToProductEvent {
    private String eventIdentifer;
    private String productId;
    private Integer productQuantity;
}
