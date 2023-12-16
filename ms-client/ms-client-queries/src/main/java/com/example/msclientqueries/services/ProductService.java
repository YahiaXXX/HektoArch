package com.example.msclientqueries.Services;

import com.example.msclientqueries.DAOs.ProductDAO;
import com.example.msclientqueries.Entities.OrderEntity;
import com.example.msclientqueries.Entities.Product;
import com.example.msclientqueries.Proxies.ProductServiceProxy;
import com.example.msclientqueries.Reposiories.OrderRepository;
import com.example.msclientqueries.Reposiories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductServiceProxy productServiceProxy;

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<ProductDAO> getAllProducts(){
        return  productServiceProxy.getAllProducts();
    }

    public ProductDAO getProductById(String productId){

        return productServiceProxy.getProductById(productId);
    }

    public List<ProductDAO> getProductsByName(String productsName){

        return  productServiceProxy.getProductsByName(productsName);
    }
//   public List<ProductDAO> getProductsByOrderId(String orderId){
//       List<Product> products=productsRepository.findAll();
//       List<OrderEntity> orderEntityList=orderRepository.findAll();
//
//        return  productsRepository.getProductsByName(orderId);
//    }





}
