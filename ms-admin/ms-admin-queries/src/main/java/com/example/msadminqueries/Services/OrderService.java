package com.example.msadminqueries.Services;

import com.example.msadminqueries.DAOs.OrderDAO;
import com.example.msadminqueries.Proxies.OrderServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderServiceProxy orderServiceProxy;

    public List<OrderDAO> getAllOrders(){
        try {
            List<OrderDAO> orderDAOList=orderServiceProxy.getAllOrders();
            return orderDAOList;
        }catch (Exception e){
            return new ArrayList<>();
        }

    }



    public OrderDAO getOrderById(String orderId){
        try {
            OrderDAO orderDAO=orderServiceProxy.getOrderById(orderId);
            return orderDAO;
        }catch (Exception e){
            return new OrderDAO();
        }

    }

    public List<OrderDAO> getOrdersByStatus(String orderStatus){
        try {
            List<OrderDAO> orderDAOList=orderServiceProxy.getOrdersByStatus(orderStatus);
            return orderDAOList;
        }catch (Exception e){
            return new ArrayList<>();
        }

    }






}
