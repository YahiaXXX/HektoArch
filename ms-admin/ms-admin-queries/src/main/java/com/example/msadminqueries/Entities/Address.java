package com.example.msadminqueries.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    String ville ;
    String wilaya ;
    String street ;
    int codePostal ;
}
