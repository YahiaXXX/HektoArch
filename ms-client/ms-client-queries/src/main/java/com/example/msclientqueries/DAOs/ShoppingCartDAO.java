package com.example.msclientqueries.DAOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoppingCartDAO {
    private String shoppingCartId;

    private String clientId;
}
