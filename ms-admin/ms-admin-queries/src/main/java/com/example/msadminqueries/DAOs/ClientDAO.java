package com.example.msadminqueries.DAOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDAO {
    private String clientId;

    private String clientName;
    private String clientEmail;

    private List<String> shopsNamesist;
}
