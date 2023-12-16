package com.example.msadminqueries.DAOs;

import com.example.msadminqueries.Entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKDAO {

    private String userId;
    private String name;

    private String email;

    private String password;

    private boolean enabled;

    private UserRole role;
}
