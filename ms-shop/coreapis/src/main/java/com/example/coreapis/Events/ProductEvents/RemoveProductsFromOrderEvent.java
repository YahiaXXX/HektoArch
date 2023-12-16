package com.example.coreapis.Events.ProductEvents;

import com.example.coreapis.DTOs.ProductToMsClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoveProductsFromOrderEvent {

    private String eventIdentifier;
//    List<ProductToMsClient> productFromoMsClientsl;
    private String orderId;

}
