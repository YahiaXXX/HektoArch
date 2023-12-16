package com.example.coreapis.Events.ProductEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoveProductFromOrderEvent {
    private String eventIdentifier;
    private String productId;

}
