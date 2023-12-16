package com.example.coreapi.Commands.OrderCommands;

import com.example.coreapi.DTOs.ProductDTO;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@XStreamAlias("AddProductToOrderCommand")
public class AddProductToOrderCommand {
//    @TargetAggregateIdentifier
//    private String eventIdentifier;

    private String productId;
    @TargetAggregateIdentifier

    private String orderId;
    private String productName;
    private Double productPrice;

    private Integer productQuantity;
    private String categoryName;
}
