package com.example.msclientqueries.Services;

import com.example.msclientqueries.DAOs.ShopDAO;
import com.example.msclientqueries.Proxies.ShopServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopServiceProxy shopServiceProxy;

    public List<ShopDAO> getAllShops() {
        return shopServiceProxy.getAllShops();
    }

    public ShopDAO getShopById(String shopId) {
        return shopServiceProxy.getShopById(shopId);
    }
    public ShopDAO getShopByName(String shopsName) {
        return shopServiceProxy.getShopByName(shopsName);
    }
    public ShopDAO getShopByShopEmail(String shopEmail){
        return shopServiceProxy.getShopByShopEmail(shopEmail);
    }
}
