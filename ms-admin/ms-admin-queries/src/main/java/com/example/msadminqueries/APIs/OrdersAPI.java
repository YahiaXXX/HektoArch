package com.example.msadminqueries.APIs;

import com.example.msadminqueries.DAOs.OrderDAO;
import com.example.msadminqueries.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersAPI {

    @Autowired
    public OrderService orderService;

    @GetMapping("/getAllOrders")
    public List<OrderDAO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderById/{orderId}")
    public OrderDAO getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }



    @GetMapping("/getOrderByStatus/{orderStatus}")
    public List<OrderDAO> getOrderByStatus(@PathVariable String orderStatus) {
        return orderService.getOrdersByStatus(orderStatus.toString());
    }
}
