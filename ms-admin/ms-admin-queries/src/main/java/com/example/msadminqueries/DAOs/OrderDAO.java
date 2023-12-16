package com.example.msadminqueries.DAOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDAO {

    private String orderId;
    private Date ordeDate;
    private String orderStatus;
    private List<ProductDAO> products;
    private Double totalPrice;

}
