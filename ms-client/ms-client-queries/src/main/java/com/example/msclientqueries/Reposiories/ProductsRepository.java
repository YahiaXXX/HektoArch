package com.example.msclientqueries.Reposiories;

import com.example.msclientqueries.DAOs.ProductDAO;
import com.example.msclientqueries.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ProductsRepository extends JpaRepository<Product,String> {

    public Optional<Product> findByProductId(String productId);

}
