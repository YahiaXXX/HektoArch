package com.example.msauth.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-client-commands")
@LoadBalancerClient(name = "ms-client-commands")
public interface ShoppingCartProxy {

    @PostMapping("/shoppingCart/create")
    public void createShoppingCart(@CookieValue(value = "token", defaultValue = "No token found in cookie") String token);

}
