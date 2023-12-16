package com.example.msadminqueries.Proxies;

import com.example.msadminqueries.DAOs.ShopDAO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-auth")
@LoadBalancerClient(name = "ms-auth")
public interface ShopServiceProxy {

    @GetMapping("/v1/api/shop/getAllShops")
    public List<ShopDAO> getAllShops();

    @GetMapping("/v1/api/shop/getShopByID")
    public ShopDAO getShopById(@RequestParam("id") String shopID);


    @GetMapping("/v1/api/shop/getShopByEmail")
    public ShopDAO getShopByShopEmail(@RequestParam("email") String email);

    @GetMapping("/v1/api/shop/getShopByEmail")
    public ShopDAO getShopByName(@RequestParam("name") String shopName);

}
