package com.example.msadminqueries.APIs;

import com.example.msadminqueries.DAOs.ProductDAO;
import com.example.msadminqueries.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductsAPI {

    @Autowired
    public ProductService productService;

    @GetMapping("/getAllProducts")
    public List<ProductDAO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{productId}")
    public ProductDAO getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }
    @GetMapping("/getProductsByName/{productName}")
    public List<ProductDAO> getProductsByName(@PathVariable String productName) {
        return productService.getProductsByName(productName);
    }

    }
