package com.example.msadmincommand.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "ms-shop-commands")
@LoadBalancerClient(name = "ms-shop-commands")

public interface ProductsProxy {


        @DeleteMapping("/products/deleteByAdmin/{productId}")
        public String deleteProductByProductIdByAdmin(@PathVariable("productId") String productId);


    }
