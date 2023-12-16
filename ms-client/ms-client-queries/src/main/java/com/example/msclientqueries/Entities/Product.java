package com.example.msclientqueries.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products_table")
public class Product {
    @Id
    private String productIdJoinColumn;
    private String productId;
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String categoryName;
   // private String shopName;
}
