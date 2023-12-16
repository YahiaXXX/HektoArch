package com.example.msclientqueries.Projections;

import com.example.coreapi.DTOs.ProductDTO;
import com.example.coreapi.Events.OrderEvents.*;
import com.example.coreapi.Events.UpdateOrderStatusEvent;
import com.example.msclientqueries.Entities.OrderEntity;
import com.example.msclientqueries.Entities.Product;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class OrderProjection {

    @Autowired
    public com.example.msclientqueries.Services.OrderService orderService;
    @EventHandler
    public void createOrderEventHandler(CreateOrderEvent event) {
        System.out.println("********** after send the creation Command and after send the Event **********");
        List<Product> products= ProductsDtoToProducts(event.getProducts());

        OrderEntity order = new OrderEntity(
                event.getOrderId(),
                event.getOrderDate(),
                event.getOrderStatus(),
                event.getClientId(),
                null,
                0.0
        );
        orderService.addOrder(order,products);
    }
    @EventHandler
    public void updateOrderEventHandler(UpdateOrderEvent event) {
        System.out.println("********** after get  the UpdateOrderEvent in updateOrderEventHandler**********");
        List<Product> products= ProductsDtoToProductsUpdate(event.getProducts());
        OrderEntity order = new OrderEntity(
                event.getOrderId(),
                event.getOrderDate(),
                event.getOrderStatus(),
                event.getClientId(),
                null,
                0.0
        );
        orderService.updateOrder(order,products);
    }
    @EventHandler
    public void updateOrderStatusEventHandler(UpdateOrderStatusEvent event) {
        System.out.println("********** after get  UpdateOrderStatusEvent**********");

        orderService.updateOrderStatus(event.getOrderId(),event.getOrderStatus());
    }
    @EventHandler
    public void addProductToOrder(AddProductToOrderEvent event) {
        System.out.println("********** after send the UPDATE Command and after send the Event **********");
        Product product=new Product(
                UUID.randomUUID().toString(),
                event.getProductId(),
                event.getProductName(),
                event.getProductPrice(),
                event.getProductQuantity(),
                event.getCategoryName()
        );
        orderService.addProductToOrder(event.getOrderId(),product);
    }
    @EventHandler
    public void addProductToOrder(AddProductsToOrderEvent event) {
        System.out.println("********** after send the UPDATE Command and after send the Event **********");
        List<Product> products=new ArrayList<>();
        event.getProductDTOList().forEach(productDTO -> {
            Product product=new Product(
                    UUID.randomUUID().toString(),
                    productDTO.getProductId(),
                    productDTO.getProductName(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    productDTO.getCategoryName()
            );
            products.add(product);
        });

        orderService.addProductsToOrder(event.getOrderId(),products);
    }


    @EventHandler
    public void removeProductFromOrder(RemoveProductFromOrderEvent event) {
        System.out.println("********** after send the UPDATE Command and after send the Event **********");
        orderService.removeProductFromOrder(event.getOrderId(), event.getProductIdJoinColumn());
    }

    private List<Product> ProductsDtoToProducts(
            List<ProductDTO> products2) {
        List<Product> products=new ArrayList<>();
        products2.forEach(productDTO -> {
            Product product=new Product(
                    UUID.randomUUID().toString(),
                    productDTO.getProductId(),
                    productDTO.getProductName(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    productDTO.getCategoryName());
            products.add(product);
        });
        return  products;

    }
    private List<Product> ProductsDtoToProductsUpdate(
            List<ProductDTO> products2) {
        List<Product> products=new ArrayList<>();
        products2.forEach(productDTO -> {
            Product product=new Product(
                    productDTO.getProductIdJoinColumn(),
                    productDTO.getProductId(),
                    productDTO.getProductName(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    productDTO.getCategoryName());
            products.add(product);
        });
        return products;
    }

    @EventHandler
    public void deleteOrderEventHandler(DeleteOrderEvent event) {
        System.out.println("********** after send the delete Command and after send the Event **********");
        orderService.deleteOrder(event.getOrderId());

    }
}
