package com.example.msshopqueries.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProductEntity {
    @Id
    private String productId;
    private String shopId;
    @Column(nullable = false)
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String productImageUrl;

    private String productDesc;

    private Double priceBefore;
    private String categoryName;

   // private String orderId;
    @OneToMany
    private List<Order> orders;

}
