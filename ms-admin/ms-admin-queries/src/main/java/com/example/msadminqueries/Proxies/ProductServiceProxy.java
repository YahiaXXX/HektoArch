package com.example.msadminqueries.Proxies;

import com.example.msadminqueries.DAOs.ProductDAO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-shop-queries")
@LoadBalancerClient(name = "ms-shop-queries")
public interface ProductServiceProxy {
    @GetMapping("/products/getAllProducts")
    public List<ProductDAO> getAllProducts();
    @GetMapping("/products/getProductById/{productId}")
    public ProductDAO getProductById(@PathVariable("productId") String productId);

    @GetMapping("/products/getProductsByName/{productName}")
    public List<ProductDAO> getProductsByName(@PathVariable("productName") String productName);
}
