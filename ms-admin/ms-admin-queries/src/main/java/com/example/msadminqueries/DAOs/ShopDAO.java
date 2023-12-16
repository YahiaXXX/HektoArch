package com.example.msadminqueries.DAOs;

import com.example.msadminqueries.Entities.Address;
import com.example.msadminqueries.Entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDAO {

    private String shopId;

    private String email;
    private String name;
    private Integer numberPhone;
    private UserRole role;
    private Address address;

    private byte[] image;
    private boolean enabled;

    private List<ProductDAO> productDAOList;
}
