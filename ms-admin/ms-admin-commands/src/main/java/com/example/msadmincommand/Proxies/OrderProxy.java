package com.example.msadmincommand.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-client-commands")
@LoadBalancerClient(name = "ms-client-commands")
public interface OrderProxy {


        @DeleteMapping("/orders/deleteByAdmin/{orderId}")
        public String deleteOrderByOrderIdByAdmin(@PathVariable("orderId") String orderId);


    }
