package com.example.msshopqueries.Projectios;

import com.example.coreapis.Events.ProductEvents.*;
import com.example.msshopqueries.Entites.ProductEntity;
import com.example.msshopqueries.Services.ProductService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component

public class ProductProjection {
    @Autowired
    private ProductService productService;



    @EventHandler
    public void createProductEventHandler(CreateProductEvent event) {
        System.out.println("********** in CreateProductEventHandler **********");
        ProductEntity product = new ProductEntity(
                event.getProductId(),
                event.getShopId(),
                event.getProductName(),
                event.getProductPrice(),
                event.getProductQuantity(),
                event.getProductImageUrl(),
                event.getProductDesc(),
                event.getPriceBefore(),
                event.getCategoryName(),
                new ArrayList<>()
        );
        productService.addProduct(product);
    }

    @EventHandler
    public void updateProductEventHandler(UpdateProductEvent event) {
        System.out.println("********** after send the UPDATE Command and after send the Event **********");
        productService.updateProduct(event);
    }
    @EventHandler
    public void updateProductEventHandler(RemoveProductsFromOrderEvent event) {
        System.out.println("********** after send the UPDATE Command and after send the Event **********");
        productService.removeProductsFromOrder(event);
    }
    @EventHandler
    public void addQuantityToProductEventHandler(AddQuantityToProductEvent event) {
        System.out.println("********** in addQuantityToProductEventHandler **********");

        productService.addQuantityToProduct(event);
    }
    @EventHandler
    public void updateProductEventHandler(AddProductToOrderEvent event) {
        System.out.println("********** AddProductToOrderEventHandler **********");

        productService.addProductToOrder(event);
    }
    @EventHandler
    public void updateProductsEventHandler(AddProductsToOrderEvent event) {
        System.out.println("********** AddProductToOrderEventHandler **********");

        productService.addProductsToOrder(event);
    }

    @EventHandler
    public void deleteProductEventHandler(DeleteProductEvent event) {
        System.out.println("********** after send the delete Command and after send the Event **********");
        productService.deleteProduct(event.getProductId());

    }
}
