package com.example.msshopqueries.Repositories;

import com.example.msshopqueries.Entites.Order;
import com.example.msshopqueries.Entites.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface ProductReopsitory extends JpaRepository<ProductEntity,String> {
    @Override
    List<ProductEntity> findAll();

    List<ProductEntity> findProductEntitiesByCategoryName(String categoryName);
    List<ProductEntity> findProductEntitiesByShopId(String shopId);
    List<ProductEntity> findProductEntitiesByOrdersContains(Order order);
    List<ProductEntity> findProductEntitiesByProductPriceIsLessThanEqual(Double price);
    List<ProductEntity> findProductEntitiesByProductPriceIsGreaterThanEqual(Double price);
}

