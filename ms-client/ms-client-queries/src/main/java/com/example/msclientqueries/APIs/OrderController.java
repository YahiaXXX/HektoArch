package com.example.msclientqueries.APIs;

import com.example.msclientqueries.DAOs.OrderDAO;
import com.example.msclientqueries.Entities.OrderEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    public final com.example.msclientqueries.Services.OrderService orderService;

    public OrderController(com.example.msclientqueries.Services.OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("/getAllOrders")
    public List<OrderDAO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderById/{orderId}")
    public OrderDAO getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }

     @GetMapping("/getOrderByClientId/{clientId}")
    public List<OrderDAO> getOrdersByClientId(@PathVariable String clientId) {
        return orderService.getOrdersByClientId(clientId);
    }

    @GetMapping("/getOrderByStatus/{orderStatus}")
    public List<OrderDAO> getOrderByStatus(@PathVariable String orderStatus) {
        return orderService.getOrdersByStatus(orderStatus);
    }
    @GetMapping("/getAllByOrderStatusAndClientId")
    public List<OrderDAO> getAllByOrderStatusAndClientId(@RequestParam("orderStatus") String orderStatus,@RequestParam("clientId") String clientId) {
        return orderService.findAllByOrderStatusAndClientId(orderStatus,clientId);
    }

}
