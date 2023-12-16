package com.example.msadminqueries.Services;

import com.example.msadminqueries.DAOs.ShopDAO;
import com.example.msadminqueries.Proxies.ShopServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopServiceProxy shopServiceProxy;

    public List<ShopDAO> getAllShops() {
        try {

            List<ShopDAO> shopDAOList= shopServiceProxy.getAllShops();
            return shopDAOList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public ShopDAO getShopById(String shopId) {
        try {
            ShopDAO shopDAO= shopServiceProxy.getShopById(shopId);
            return shopDAO;
        }catch (Exception e){
            return new ShopDAO();
        }
    }
    public ShopDAO getShopByName(String shopsName) {
        try {
            ShopDAO shopDAO= shopServiceProxy.getShopByName(shopsName);
            return shopDAO;
        }catch (Exception e){
            return new ShopDAO();
        }
    }
    public ShopDAO getShopByShopEmail(String shopEmail){
        try {
            ShopDAO shopDAO= shopServiceProxy.getShopByShopEmail(shopEmail);
            return shopDAO;
        }catch (Exception e){
            return new ShopDAO();
        }
    }
}
