package com.example.msclientcommands.Aggregate;
import com.example.coreapi.Commands.PostCommands.DeletePostCommand;
import com.example.coreapi.Commands.PostCommands.UpdatePostCommand;
import com.example.coreapi.Events.PostEvents.CreatePostEvent;
import com.example.coreapi.Events.PostEvents.DeletePostEvent;
import com.example.coreapi.Events.PostEvents.UpdatePostEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import com.example.coreapi.Commands.PostCommand.CreatePostCommand;
import java.util.UUID;

@Aggregate

public class PostAggregate {
    @AggregateIdentifier
    private String eventIdentifier;

    private String postId;
    public PostAggregate() {
    }

    @CommandHandler
    public PostAggregate(CreatePostCommand command) {


        // Validate the command and perform business logic
        System.out.println("after create post and before create the event command");

        // Create a user creation event
        CreatePostEvent event = new CreatePostEvent(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                command.getPostTitle(),
                command.getPostDescription(),
                command.getPostImageUrl(),
                command.getClientId());

        // Apply the event to the aggregate
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreatePostEvent event) {
        this.eventIdentifier = event.getEventIdentifier();
        System.out.println("after create post and after create the event command");

        this.postId = event.getPostId();
//        this.postName = event.getPostName();
    }

    //UPDATE
    @CommandHandler
    public PostAggregate(UpdatePostCommand command) {

        System.out.println("********** after send the UPDATE Command and before send the Event **********");
        UpdatePostEvent event = new UpdatePostEvent(
                UUID.randomUUID().toString(),
                command.getPostId(),
                command.getPostTitle(),
                command.getPostDescription(),
                command.getPostImageUrl(),
                command.getClientId()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UpdatePostEvent event) {
        System.out.println("********** get the UPDATE Event in aggregate class **********");

        this.eventIdentifier = event.getEventIdentifier();
//        this.postId = event.getPostId();
//        this.postName = event.getPostName();


    }

    // DELETE
    @CommandHandler
    public PostAggregate(DeletePostCommand command) {

        System.out.println("********** after send the Command and before send the Event **********");
        DeletePostEvent event = new DeletePostEvent(UUID.randomUUID().toString(), command.getPostId());
        AggregateLifecycle.apply(event);


    }

    @EventSourcingHandler
    public void on(DeletePostEvent event) {

        System.out.println("********** get the Event in aggregate class **********");
        this.eventIdentifier = event.getEventIdentifier();

//        this.postId = event.getPostId();
//        this.postName = event.getPostName();


    }

}
