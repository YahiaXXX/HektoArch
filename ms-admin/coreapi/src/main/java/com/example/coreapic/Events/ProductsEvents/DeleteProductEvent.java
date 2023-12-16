package com.example.coreapic.Events.ProductsEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductEvent {
    private String eventIdentifier;

    private String productId;

}
