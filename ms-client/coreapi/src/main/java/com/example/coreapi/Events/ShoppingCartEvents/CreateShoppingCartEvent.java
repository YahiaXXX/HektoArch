package com.example.coreapi.Events.ShoppingCartEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShoppingCartEvent {
    private String eventIdentifier;
    private String shoppingCartId;

    private String clientId;
}
