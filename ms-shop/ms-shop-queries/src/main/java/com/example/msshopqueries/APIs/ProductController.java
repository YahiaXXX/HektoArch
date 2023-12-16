package com.example.msshopqueries.APIs;

import com.example.msshopqueries.DAOs.ProductDAO;
import com.example.msshopqueries.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }


    @GetMapping("/getAllProducts")
    public List<ProductDAO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{productId}")
    public ProductDAO getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/getProductByCategoryName/{categoryName}")
    public List<ProductDAO> getProductByCategoryName(@PathVariable String categoryName) {
        return productService.findProductsByCategoryName(categoryName);
    }

    @GetMapping("/getProductsByClientId/{shopId}")
    public List<ProductDAO> getProductsByClientId(@PathVariable String shopId) {
        return productService.findProductEntitiesByShopId(shopId);
    }
//    @GetMapping("/getProductsByOrderId/{orderId}")
//    public List<ProductDAO> getProductsByOrderId(@PathVariable String orderId) {
//        return productService.findProductEntitiesByOrderId(orderId);
//    }
    @GetMapping("/findProductEntitiesByProductPriceIsLessThanEqual")
    public List<ProductDAO> findProductEntitiesByProductPriceIsLessThanEqual(@RequestParam("price") Double price) {
        return productService.findProductEntitiesByProductPriceIsLessThanEqual(price);
    }
    @GetMapping("/findProductEntitiesByProductPriceIsGreaterThanEqual")
    public List<ProductDAO> findProductEntitiesByProductPriceIsGreaterThanEqual(@RequestParam("price") Double price) {
        return productService.findProductEntitiesByProductPriceIsGreaterThanEqual(price);
    }
}
