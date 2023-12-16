package com.example.msclientqueries.Reposiories;


import com.example.coreapi.DTOs.OrderStatus;
import com.example.msclientqueries.Entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<OrderEntity,String> {

//    @Override
//    List<OrderEntity> findAll(Sort sort);

    public List<OrderEntity> findAllByOrderStatus(OrderStatus orderStatus);

    public List<OrderEntity> findAllByOrderStatusAndClientId(OrderStatus orderStatus,String clientId);


    public List<OrderEntity> findOrderEntitiesByClientId(String clientId);

}