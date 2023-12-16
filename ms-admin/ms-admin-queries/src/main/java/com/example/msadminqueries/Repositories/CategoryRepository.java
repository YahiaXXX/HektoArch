package com.example.msadminqueries.Repositories;

import com.example.msadminqueries.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<CategoryEntity,String> {
    @Override
    List<CategoryEntity> findAll();

    public Optional<CategoryEntity> findCategoryEntityByCategoryName(String categoryName);
}
