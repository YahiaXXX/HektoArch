package com.example.msclientcommands.Aggregate;

import com.example.coreapi.Commands.ShoppingCartCommands.CreateShoppingCartCommand;
import com.example.coreapi.Commands.ShoppingCartCommands.EmptyShoppingCartCommand;
import com.example.coreapi.Events.ShoppingCartEvents.CreateShoppingCartEvent;
import com.example.coreapi.Events.ShoppingCartEvents.EmptyShoppingCartEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate

public class ShoppingCartAggregate {
    @AggregateIdentifier
    private String eventIdentifier;
    public ShoppingCartAggregate() {
    }
    @CommandHandler
    public ShoppingCartAggregate(CreateShoppingCartCommand command) {


        // Validate the command and perform business logic
        System.out.println("after create post and before create the event command");

        // Create a user creation event
        CreateShoppingCartEvent event = new CreateShoppingCartEvent(
                UUID.randomUUID().toString(),
                command.getShoppingCartId(),
                command.getClientId()
                );

        // Apply the event to the aggregate
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateShoppingCartEvent event) {
        this.eventIdentifier = event.getEventIdentifier();
        System.out.println("after create post and after create the event command");
    }

    //UPDATE
    @CommandHandler
    public ShoppingCartAggregate(EmptyShoppingCartCommand command) {

        System.out.println("********** after send the UPDATE Command and before send the Event **********");
        EmptyShoppingCartEvent event = new EmptyShoppingCartEvent(
                UUID.randomUUID().toString(),
                command.getShoppingCartId(),
                command.getClientId()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EmptyShoppingCartEvent event) {
        System.out.println("********** get the UPDATE Event in aggregate class **********");

        this.eventIdentifier = event.getEventIdentifier();

    }
}
