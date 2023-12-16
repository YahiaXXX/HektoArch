package com.example.msclientqueries.Entities;

import com.example.coreapi.DTOs.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="order_table")
public class OrderEntity {

    @Id
    @Column(unique = true)
    private String orderId;
    private Date ordeDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;


 //   @OneToOne(mappedBy = "orderEntity")
//    private String shoppingCartId;

    private String clientId;
//    @ElementCollection
//    private List<String> productsId;
    @OneToMany
    private List<Product> products;

    private Double totalPrice;

}
