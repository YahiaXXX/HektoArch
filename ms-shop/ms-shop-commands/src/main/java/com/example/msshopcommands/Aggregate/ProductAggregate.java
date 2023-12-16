package com.example.msshopcommands.Aggregate;

import com.example.coreapis.Commands.ProductCommands.*;
import com.example.coreapis.Events.ProductEvents.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate

public class ProductAggregate {
    @AggregateIdentifier
    private String eventIdentifier;

    private String productId;
    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {

        // Validate the command and perform business logic
        System.out.println("in CreateProductCommand Aggreagate ***********************");

        // Create a user creation event
        CreateProductEvent event = new CreateProductEvent(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                command.getShopId(),
                command.getProductName(),
                command.getProductPrice(),
                command.getProductQuantity(),
                command.getProductImageUrl(),
                command.getProductDesc(),
                command.getProductPrice(),
                command.getCategoryName(),
                null
                );

        // Apply the event to the aggregate
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateProductEvent event) {
        this.eventIdentifier = event.getEventIdentifier();
        System.out.println("in CreateProductEvent  @EventSourcingHandler ***********************");

 //       this.productId = event.getProductId();
//        this.productName = event.getProductName();
    }

    //UPDATE
    @CommandHandler
    public ProductAggregate(RemoveProductsFromOrderCommand command) {

        System.out.println("********** after send the UPDATE Command and before send the Event **********");
        RemoveProductsFromOrderEvent event = new RemoveProductsFromOrderEvent(
                UUID.randomUUID().toString(),
                command.getOrderId()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(RemoveProductsFromOrderEvent event) {
        System.out.println("********** get the UPDATE Event in aggregate class **********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.productId = event.getProductId();
//        this.productName = event.getProductName();


    }

    @CommandHandler
    public ProductAggregate(UpdateProductCommand command) {

        System.out.println("********** after send the UPDATE Command and before send the Event **********");
        UpdateProductEvent event = new UpdateProductEvent(
                UUID.randomUUID().toString(),
                command.getProductId(),
                command.getShopId(),
                command.getProductName(),
                command.getProductPrice(),
                command.getProductQuantity(),
                command.getProductImageUrl(),
                command.getProductDesc(),
              //  command.getPriceBefore(),
                command.getCategoryName(),
             null
             //   command.getOrderId()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UpdateProductEvent event) {
        System.out.println("********** get the UPDATE Event in aggregate class **********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.productId = event.getProductId();
//        this.productName = event.getProductName();


    }

 @CommandHandler
    public ProductAggregate(AddQuantityToProductCommand command) {

        System.out.println("********** in AddProductToOrderCommandHandler **********");
        AddQuantityToProductEvent event = new AddQuantityToProductEvent(
                UUID.randomUUID().toString(),
                command.getProductId(),
                command.getProductQuantity()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AddQuantityToProductEvent event) {
        System.out.println("********** in AddProductToOrderEvent EventSourcingHandler**********");

        this.eventIdentifier = event.getEventIdentifer();
//        this.productId = event.getProductId();
//        this.productName = event.getProductName();


    }

 @CommandHandler
    public ProductAggregate(AddProductToOrderCommand command) {

        System.out.println("********** in AddProductToOrderCommandHandler **********");
        AddProductToOrderEvent event = new AddProductToOrderEvent(
                UUID.randomUUID().toString(),
                command.getProductId(),
                command.getProductQuantity(),
                command.getOrder()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AddProductToOrderEvent event) {
        System.out.println("********** in AddProductToOrderEvent EventSourcingHandler**********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.productId = event.getProductId();
//        this.productName = event.getProductName();


    }

    @CommandHandler
    public ProductAggregate(AddProductsToOrderCommand command) {

        System.out.println("********** in AddProductToOrderCommandHandler **********");
        AddProductsToOrderEvent event = new AddProductsToOrderEvent(
                UUID.randomUUID().toString(),
                command.getProductsIds(),
                command.getProductsQuantities(),
                command.getOrder()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AddProductsToOrderEvent event) {
        System.out.println("********** in AddProductToOrderEvent EventSourcingHandler**********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.productId = event.getProductId();
//        this.productName = event.getProductName();


    }

    // DELETE
    @CommandHandler
    public ProductAggregate(DeleteProductCommand command) {

        System.out.println("********** after send the Command and before send the Event **********");
        DeleteProductEvent event = new DeleteProductEvent(UUID.randomUUID().toString(), command.getProductId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeleteProductEvent event) {

        System.out.println("********** get the Event in aggregate class **********");
        this.eventIdentifier = event.getEventIdentifier();

//        this.productId = event.getProductId();
//        this.productName = event.getProductName();


    }
}
