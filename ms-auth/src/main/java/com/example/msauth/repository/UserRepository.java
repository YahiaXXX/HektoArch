package com.example.msauth.repository;

import com.example.msauth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    User findByUserId(String usedId);



    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.userId = ?1")
    int enableAppUser(String email);

}
