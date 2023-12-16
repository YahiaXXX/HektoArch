package com.example.msclientqueries.APIs;

import com.example.msclientqueries.DAOs.ShoppingCartDAO;
import com.example.msclientqueries.Services.ShoppingCartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    public final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService=shoppingCartService;
    }

    @GetMapping("/getShoppingCartById/{shoppingCartId}")
    public ShoppingCartDAO getShoppingCartById(@PathVariable String shoppingCartId) {
        return shoppingCartService.getShoppingCartById(shoppingCartId);
    }

    @GetMapping("/getShoppingCartByClientId/{clientId}")
    public ShoppingCartDAO getShoppingCartsByClientId(@PathVariable String clientId) {
        return shoppingCartService.getShoppingCartByClientId(clientId);
    }

}
