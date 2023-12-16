package com.example.msshopcommands.Proxies;

import com.example.coreapis.DTOs.ProductToMsClient;
import com.example.coreapis.DTOs.ProductUpdateDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-client-commands")
@LoadBalancerClient(name = "ms-client-commands")
public interface MsClientCommandsProxy {
    @PostMapping("/orders/addProductToOrderfromShop/{orderId}")
    public ResponseEntity<String> addProductToOrder(@RequestBody ProductToMsClient productToMsClient, @PathVariable String orderId);
    @PostMapping("/orders/addProductsToOrderFromShop/{orderId}")
    public ResponseEntity<String> addProductsToOrder(@RequestBody List<ProductToMsClient> productToMsClients, @PathVariable String orderId);

//    @RequestMapping(method = RequestMethod.POST, value = "/orders/addProductToOrderfromShop/{orderId}")
//    void addProductToOrder(@PathVariable("orderId") String orderId, @RequestBody ProductToMsClient product);

}
