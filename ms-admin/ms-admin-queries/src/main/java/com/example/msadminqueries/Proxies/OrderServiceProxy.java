package com.example.msadminqueries.Proxies;

import com.example.msadminqueries.DAOs.OrderDAO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-client-queries")
@LoadBalancerClient(name = "ms-client-queries")
public interface OrderServiceProxy {
    @GetMapping("/orders/getAllOrders")
    public List<OrderDAO> getAllOrders();
    @GetMapping("/orders/getOrderById/{orderId}")
    public OrderDAO getOrderById(@PathVariable("orderId") String orderId);

    @GetMapping("/orders/getOrderByStatus/{orderStatus}")
    public List<OrderDAO> getOrdersByStatus(@PathVariable String orderStatus);

    @GetMapping("/orders/getOrderByClientId/{clientId}")
    public List<OrderDAO> getOrdersByClientId(@PathVariable String clientId);

}
