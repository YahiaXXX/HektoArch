package com.example.msauth.repository;

import com.example.msauth.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


public interface ConfirmationTokenRepository
        extends JpaRepository<Token, Long> {

    Token findByToken(String token);


    @Query("SELECT t FROM Token t WHERE t.user_id = :userId")
    Token findByUserId(String userId);
    @Transactional
    @Modifying
    @Query("UPDATE Token c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
