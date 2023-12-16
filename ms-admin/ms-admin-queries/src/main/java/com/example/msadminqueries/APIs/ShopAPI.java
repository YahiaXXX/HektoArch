package com.example.msadminqueries.APIs;

import com.example.msadminqueries.DAOs.ShopDAO;
import com.example.msadminqueries.Services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopAPI {

    @Autowired
    public ShopService shopService;


    @GetMapping("/getAllShops")
    public List<ShopDAO> getAllShops() {
        return shopService.getAllShops();
    }
    @GetMapping("/getShopById/{shopId}")
    public ShopDAO getShopById(@PathVariable String shopId) {
        return shopService.getShopById(shopId);
    }

    @GetMapping("/getShopByEmail/{shopEmail}")
    public ShopDAO getShopByEmail(@PathVariable String shopEmail) {
        return shopService.getShopByShopEmail(shopEmail);
    }
    @GetMapping("/getShopsByName/{shopsName}")
    public ShopDAO getShopByName(@PathVariable String shopsName) {
        return shopService.getShopByName(shopsName);
    }

}
