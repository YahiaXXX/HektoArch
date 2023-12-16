package com.example.msadmincommand.Proxies;

import com.example.coreapic.DTO.CategoryDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-admin-queries")
@LoadBalancerClient(name = "ms-admin-queries")
public interface CategoryProxy {
    @GetMapping("/categories/getCategoryByName/{categoryName}")
    public Optional<CategoryDTO> getCategoryByName(@PathVariable("categoryName") String categoryName);
    @GetMapping("/categories/getCategoryById/{categoryId}")
    public Optional<CategoryDTO> getCategoryById(@PathVariable("categoryId") String categoryId);
}
