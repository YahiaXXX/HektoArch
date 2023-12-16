package com.example.msadminqueries.DAOs;


import com.example.msadminqueries.Entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDAO {

    private String productId;
    private String productName;
    private Integer productPrice;

    private Integer productQuantity;
    private String categoryName;


}
