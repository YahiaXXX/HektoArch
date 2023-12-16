package com.example.coreapi.Commands.OrderCommands;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@XStreamAlias("RemoveProductFromOrderCommand")

public class RemoveProductFromOrderCommand {
//    @TargetAggregateIdentifier
//    private String eventIdentifier;
    private String productIdJoinColumn;
    @TargetAggregateIdentifier

    private String orderId;

}
