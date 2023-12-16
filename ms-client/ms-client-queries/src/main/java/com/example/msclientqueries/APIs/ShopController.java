package com.example.msclientqueries.APIs;

import com.example.msclientqueries.DAOs.ShopDAO;
import com.example.msclientqueries.Services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    public ShopService shopService;
    @GetMapping("/getAllShops")
    public List<ShopDAO> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/getShopByEmail/{shopEmail}")
    public ShopDAO getShopByEmail(@PathVariable String shopEmail) {
        return shopService.getShopByShopEmail(shopEmail);
    }
    @GetMapping("/getShopsByName/{shopsName}")
    public ShopDAO getShopsByName(@PathVariable String shopsName) {
        return shopService.getShopByName(shopsName);
    }
}
