package com.example.msauth.repository;

import com.example.msauth.entities.Shop;
import com.example.msauth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShopRepository extends JpaRepository<Shop,String> {
    Shop findByEmail(String email);

    Shop findByShopId(String shopId);

//    Shop findByName(String shopName);
    @Modifying
    @Query("UPDATE Shop e SET e.image = :image WHERE e.shopId = :shopId")
    void updateImageById(@Param("shopId") String shopId, @Param("image") String image);


    @Transactional
    @Modifying
    @Query("UPDATE Shop a " +
            "SET a.image = :image WHERE a.shopId = ?1")
    int updateImage( String image);

}
