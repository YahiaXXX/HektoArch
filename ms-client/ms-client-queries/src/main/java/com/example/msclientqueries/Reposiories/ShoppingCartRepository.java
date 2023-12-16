package com.example.msclientqueries.Reposiories;

import com.example.msclientqueries.Entities.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity,String> {

    public Optional<ShoppingCartEntity> findShoppingCartEntityByClientId(String clientId);
}
