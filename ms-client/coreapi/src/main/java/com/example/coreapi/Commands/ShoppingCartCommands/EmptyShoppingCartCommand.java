package com.example.coreapi.Commands.ShoppingCartCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmptyShoppingCartCommand {
    private String shoppingCartId;

    private String clientId;
}
