package com.example.coreapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {


    private String shoppingCartId;

    private String clientId;
}
