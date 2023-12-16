package com.example.msclientcommands.Proxies;

import com.example.coreapi.DTOs.PostDTO;
import com.example.coreapi.DTOs.ShoppingCartDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-client-queries")
@LoadBalancerClient(name = "ms-client-queries")
public interface ShoppingCartProxy {

    @GetMapping("/shoppingCart/getShoppingCartByClientId/{clientId}")
    public Optional<ShoppingCartDTO> getShoppingCartByClientId(@PathVariable("clientId") String clientId);
}
