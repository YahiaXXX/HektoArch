package com.example.msclientqueries.Proxies;

import com.example.msclientqueries.DAOs.CategoryDAO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-admin-queries")
@LoadBalancerClient(name = "ms-admin-queries")

public interface CategoryProxy {
    @GetMapping("/categories/getAll")
    public List<CategoryDAO> getAllCategories();

    @GetMapping("/categories/getCategoryById/{categoryId}")
    public CategoryDAO getCategoryById(@PathVariable String categoryId);

    @GetMapping("/categories/getCategoryByName/{categoryName}")
    public Optional<CategoryDAO> getCategoryByCategoryName(@PathVariable String categoryName) ;
}
