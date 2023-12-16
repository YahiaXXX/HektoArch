package com.example.msadminqueries.Services;

import com.example.msadminqueries.DAOs.ProductDAO;
import com.example.msadminqueries.Proxies.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {

    @Autowired
    private ProductServiceProxy productServiceProxy;

    public List<ProductDAO> getAllProducts(){
        try {
            List<ProductDAO> productDAOList=productServiceProxy.getAllProducts();
            return  productDAOList;

        }catch (Exception e){
            return  new ArrayList<>();

        }
    }

    public ProductDAO getProductById(String productId){
        try {
            ProductDAO productDAO=productServiceProxy.getProductById(productId);
            return  productDAO;

        }catch (Exception e){
            return  new ProductDAO();
        }
    }

    public List<ProductDAO> getProductsByName(String productsName){
        try {
            List<ProductDAO> productDAOList=productServiceProxy.getProductsByName(productsName);
            return  productDAOList;

        }catch (Exception e){
            return  new ArrayList<>();
        }
    }





}
