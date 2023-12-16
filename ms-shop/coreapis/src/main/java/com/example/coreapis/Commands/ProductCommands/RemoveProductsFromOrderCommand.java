package com.example.coreapis.Commands.ProductCommands;

import com.example.coreapis.DTOs.ProductToMsClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoveProductsFromOrderCommand {
    //List<ProductToMsClient> productFromoMsClientsl;

    private String orderId;

}
