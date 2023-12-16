package com.example.msadmincommand.Aggregate;

import com.example.coreapic.Commands.CategoriesCommands.CreateCategoryCommand;
import com.example.coreapic.Commands.CategoriesCommands.DeleteCategoryCommand;
import com.example.coreapic.Commands.CategoriesCommands.UpdateCategoryCommand;
import com.example.coreapic.Events.CategoriesEvents.CreateCategoryEvent;
import com.example.coreapic.Events.CategoriesEvents.DeleteCategoryEvent;
import com.example.coreapic.Events.CategoriesEvents.UpdateCategoryEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate

public class CategoryAggregate {
    @AggregateIdentifier
    private String eventIdentifier;

//    private String categoryId;
//    private String categoryName;
//
//    private List<ProductEntity> productEntityList;


    public CategoryAggregate() {}

    //CREATE
    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        // Validate the command and perform business logic

        // Create a user creation event
        CreateCategoryEvent event = new CreateCategoryEvent(UUID.randomUUID().toString(),command.getCategoryId(), command.getCategoryName());

        // Apply the event to the aggregate
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateCategoryEvent event) {
        this.eventIdentifier = event.getEventIdentifier();

//        this.categoryId = event.getCategoryId();
//        this.categoryName = event.getCategoryName();
    }

    //UPDATE
    @CommandHandler
    public CategoryAggregate(UpdateCategoryCommand command) {

        System.out.println("********** after send the UPDATE Command and before send the Event **********");
        UpdateCategoryEvent event = new UpdateCategoryEvent(
                UUID.randomUUID().toString(),
                command.getCategoryId(),
                command.getCategoryName()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UpdateCategoryEvent event) {
        System.out.println("********** get the UPDATE Event in aggregate class **********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.categoryId = event.getCategoryId();
//        this.categoryName = event.getCategoryName();


    }
    // DELETE
    @CommandHandler
    public CategoryAggregate(DeleteCategoryCommand command) {

        System.out.println("********** after send the Command and before send the Event **********");
        DeleteCategoryEvent event = new DeleteCategoryEvent(UUID.randomUUID().toString(),command.getCategoryId(),"");
        AggregateLifecycle.apply(event);


    }

    @EventSourcingHandler
    public void on(DeleteCategoryEvent event) {

        System.out.println("********** get the Event in aggregate class **********");
        this.eventIdentifier = event.getEventIdentifier();

//        this.categoryId = event.getCategoryId();
//        this.categoryName = event.getCategoryName();


    }
}
