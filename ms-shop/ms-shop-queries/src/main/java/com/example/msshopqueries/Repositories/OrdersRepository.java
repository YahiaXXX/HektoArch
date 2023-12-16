package com.example.msshopqueries.Repositories;

import com.example.coreapis.DTOs.OrderDTO;
import com.example.msshopqueries.Entites.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RepositoryRestResource
public interface OrdersRepository extends JpaRepository<Order,String> {

    Optional<Order> findOrderByOrderId(String orderId);

}
