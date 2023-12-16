package com.example.msshopqueries.Proxies;

import com.example.coreapis.DTOs.OrderDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "ms-client-commands")
@LoadBalancerClient(name = "ms-client-commands")
public interface OrdersProxy {
    @GetMapping("/orders/getOrderById/{orderId}")
    public OrderDTO getOrderById(@PathVariable String orderId);

}
