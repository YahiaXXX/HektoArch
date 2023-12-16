package com.example.msclientcommands.Aggregate;

import com.example.coreapi.Commands.OrderCommands.*;
import com.example.coreapi.Events.OrderEvents.*;
import com.example.coreapi.Events.UpdateOrderStatusEvent;
import com.example.msclientcommands.Proxies.ProductCommandProxy;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.UUID;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String eventIdentifier;

//   private String orderId;
//    private String orderName;
//
//    private List<ProductEntity> productEntityList;



    public OrderAggregate() {
    }

    //CREATE
    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {

        String orderId = UUID.randomUUID().toString();

        // Validate the command and perform business logic
        System.out.println("before creation order Event");

        // Create a user creation event
        CreateOrderEvent event = new CreateOrderEvent(
                UUID.randomUUID().toString(),
                orderId,
                command.getOrderDate(),
                command.getOrderStatus(),
//                command.getShoppingCartId(),
                command.getClientId(),
                command.getProducts());
        System.out.println("after creation order Event");

        // Apply the event to the aggregate
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateOrderEvent event) {

        System.out.println("in creation order Event handler");

        this.eventIdentifier = event.getEventIdentifier();

//        this.orderId = event.getOrderId();
//        this.orderName = event.getOrderName();
    }

    //UPDATE

    @CommandHandler
    public OrderAggregate(UpdateOrderCommand command) {

        System.out.println("********** after send the UpdateOrderCommand and before send the UpdateOrderEVent **********");
        UpdateOrderEvent event = new UpdateOrderEvent(
                UUID.randomUUID().toString(),
                command.getOrderId(),
                command.getOrderDate(),
                command.getOrderStatus(),
                command.getClientId(),
                command.getProducts()
        );
        AggregateLifecycle.apply(event);

    }
    @EventSourcingHandler
    public void on(UpdateOrderEvent event) {
        System.out.println("********** get the UpdateOrderEvent in aggregate class **********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.orderId = event.getOrderId();
//        this.orderName = event.getOrderName();

    }
    @CommandHandler
    public OrderAggregate(UpdateOrderStateCommand command) {

        System.out.println("********** after send the UpdateOrderStateCommand and before send the UpdateOrderStateEvent **********");
        UpdateOrderStatusEvent event = new UpdateOrderStatusEvent(
//                command.getEventIdentifier(),
                UUID.randomUUID().toString(),
                command.getOrderId(),
                command.getOrderStatus()
        );
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(UpdateOrderStatusEvent event) {
        System.out.println("********** after send the UpdateOrderStateCommand and after send the UpdateOrderStateEvent **********");
        this.eventIdentifier = event.getEventIdentifier();
//        this.orderId = event.getOrderId();
//        this.orderName = event.getOrderName();

    }
    @CommandHandler
    public OrderAggregate(AddProductsToOrderCommand command) {

        // Validate the command and perform business logic
        System.out.println("in AddProductToOrderCommand handler");
        AddProductsToOrderEvent event = new AddProductsToOrderEvent(
//                command.getEventIdentifier(),
                UUID.randomUUID().toString(),
               command.getOrderId(),
               command.getProductDTOList()
        );
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(AddProductsToOrderEvent event) {
        System.out.println("in AddProductToOrderEvent handler");
        this.eventIdentifier = event.getEventIdentifier();
//        this.orderId = event.getOrderId();
//        this.orderName = event.getOrderName();

    }

    @CommandHandler
    public OrderAggregate(AddProductToOrderCommand command) {

        // Validate the command and perform business logic
        System.out.println("in AddProductToOrderCommand handler");
        AddProductToOrderEvent event = new AddProductToOrderEvent(
//                command.getEventIdentifier(),
                UUID.randomUUID().toString(),
                command.getProductId(),
                command.getOrderId(),
                command.getProductName(),
                command.getProductPrice(),
                command.getProductQuantity(),
                command.getCategoryName()
        );
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(AddProductToOrderEvent event) {
        System.out.println("in AddProductToOrderEvent handler");
        this.eventIdentifier = event.getEventIdentifier();
//        this.orderId = event.getOrderId();
//        this.orderName = event.getOrderName();

    }
    @CommandHandler
    public OrderAggregate(RemoveProductFromOrderCommand command) {

        System.out.println("********** after send the UPDATE Command and before send the Event **********");
        RemoveProductFromOrderEvent event = new RemoveProductFromOrderEvent(
//                command.getEventIdentifier(),
                UUID.randomUUID().toString(),
                command.getProductIdJoinColumn(),
                command.getOrderId()
        );
        AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void on(RemoveProductFromOrderEvent event) {
        System.out.println("********** get the UPDATE Event in aggregate class **********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.orderId = event.getOrderId();
//        this.orderName = event.getOrderName();

    }

    // DELETE
    @CommandHandler
    public OrderAggregate(DeleteOrderCommand command) {

        System.out.println("********** after send the Command and before send the Event **********");
        DeleteOrderEvent event = new DeleteOrderEvent(UUID.randomUUID().toString(), command.getOrderId());
        AggregateLifecycle.apply(event);


    }

    @EventSourcingHandler
    public void on(DeleteOrderEvent event) {

        System.out.println("********** get the Event in aggregate class **********");
        this.eventIdentifier = event.getEventIdentifier();

//        this.orderId = event.getOrderId();
//        this.orderName = event.getOrderName();


    }
}
