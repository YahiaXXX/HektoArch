package com.example.coreapi.Commands.OrderCommands;

import com.example.coreapi.DTOs.OrderStatus;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@XStreamAlias("UpdateOrderStateCommand")

public class UpdateOrderStateCommand {
//    @TargetAggregateIdentifier
//    private String eventIdentifier;

    private String orderId;
    private OrderStatus orderStatus;

}
