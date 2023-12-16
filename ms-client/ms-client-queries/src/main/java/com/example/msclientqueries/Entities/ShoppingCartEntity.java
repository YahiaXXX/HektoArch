package com.example.msclientqueries.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="shoppingCart_table")
public class ShoppingCartEntity {
    @Id
    private String shoppingCartId;

   @Column(unique = true)
    private String clientId;

}