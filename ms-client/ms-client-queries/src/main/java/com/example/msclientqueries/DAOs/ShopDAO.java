package com.example.msclientqueries.DAOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopDAO {
    private String shopId;

    private String shopEmail;
    private String shopName;

    private List<ProductDAO> productDAOList;
}
