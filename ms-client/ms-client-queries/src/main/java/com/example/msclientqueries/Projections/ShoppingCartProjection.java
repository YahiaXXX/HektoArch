package com.example.msclientqueries.Projections;

import com.example.coreapi.Events.ShoppingCartEvents.CreateShoppingCartEvent;
import com.example.coreapi.Events.ShoppingCartEvents.EmptyShoppingCartEvent;
import com.example.msclientqueries.Entities.ShoppingCartEntity;
import com.example.msclientqueries.Services.ShoppingCartService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ShoppingCartProjection {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @EventHandler
    public void createShoppingCartEventHandler(CreateShoppingCartEvent event) {
        System.out.println("********** after send the creation Command and after send the Event **********");
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity(
                event.getShoppingCartId(),
                event.getClientId()
        );
        shoppingCartService.CreateShoppingCart(shoppingCart);
    }


    //Empty event handler must be in ms-shop
//    @EventHandler
//    public void emptyShoppingCartEventHandler(EmptyShoppingCartEvent event) {
//        System.out.println("********** after send the UPDATE Command and after send the Event **********");
//        ShoppingCartEntity post = new ShoppingCartEntity(
//                event.getShoppingCartId(),
//
//                event.getClientId()
//        );
//        shoppingCartService.updateShoppingCart(post);
//    }

}
