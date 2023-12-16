package com.example.msclientqueries.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms-shop-commands")
@LoadBalancerClient(name = "ms-shop-commands")
public interface ProductCommandProxy {

    @PostMapping("/products/removeProductsFromOrder/{orderId}")
    public void removeProductsFromOrder(@PathVariable String orderId);
}

