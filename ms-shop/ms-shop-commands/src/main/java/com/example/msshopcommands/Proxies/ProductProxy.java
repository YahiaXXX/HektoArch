package com.example.msshopcommands.Proxies;

import com.example.coreapis.DTOs.ProductDTO;
import com.example.coreapis.DTOs.ProductUpdateDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-shop-queries")
@LoadBalancerClient(name = "ms-shop-queries")
public interface ProductProxy {

    @GetMapping("/products/getProductById/{productId}")
    public Optional<ProductUpdateDTO> getProductById(@PathVariable("productId") String productId);

}
