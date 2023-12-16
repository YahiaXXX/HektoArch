package com.example.coreapic.Events.ClientEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteClientEvent {
    private String eventIdentifier;


    private String clientId;

}
