package com.example.msshopcommands.Proxies;

import com.example.coreapis.DTOs.OrderDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-client-queries")
@LoadBalancerClient(name = "ms-client-queries")
public interface MsClientQueriesProxy {


    @GetMapping("/orders/getOrderById/{orderId}")
    public OrderDTO getOrderById(@PathVariable String orderId);


}
