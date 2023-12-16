package com.example.msshopqueries.Entites;

import com.example.coreapis.DTOs.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @Column(unique = true)
    private String orderProductjoinCloumn;

    @Column(unique = true)
    private String orderId;

    private Date orderDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    private String clientId;

   // private Double totalPrice;


}
