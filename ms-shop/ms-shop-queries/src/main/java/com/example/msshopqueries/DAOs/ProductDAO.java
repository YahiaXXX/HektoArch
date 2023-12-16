package com.example.msshopqueries.DAOs;

import com.example.msshopqueries.Entites.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDAO {

    private String productId;
    private String shopId;
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String productImageUrl;

    private String productDesc;

    private Double priceBefore;
    private String categoryName;

    private List<Order> orders;
}
