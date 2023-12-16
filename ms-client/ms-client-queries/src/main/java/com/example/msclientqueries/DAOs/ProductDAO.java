package com.example.msclientqueries.DAOs;

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
    private Integer productPrice;

    private Integer productQuantity;
    private String productImageUrl;

    private Integer productDesc;

    private Integer priceBefore;

    private String categoryName;

    private List<String> orders;


}
