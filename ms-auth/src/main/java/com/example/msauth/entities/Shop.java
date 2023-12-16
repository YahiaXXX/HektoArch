package com.example.msauth.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    private String shopId;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;

    private String password;


    private boolean active;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Embedded
    private Address address;

    private String numberPhone;



    private String image;


}
