package com.tarabut.customer.cmd.aggregates;

import com.tarabut.customer.cmd.commands.AddCustomerCommand;
import com.tarabut.customer.cmd.commands.DeleteCustomerCommand;
import com.tarabut.customer.cmd.commands.UpdateCustomerCommand;
import com.tarabut.customer.core.enums.Preference;
import com.tarabut.customer.core.events.AddCustomerEvent;
import com.tarabut.customer.core.events.DeleteCustomerEvent;
import com.tarabut.customer.core.events.UpdateCustomerEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

@Slf4j
@NoArgsConstructor
@Aggregate
class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private Preference preference;


    @CommandHandler
    public CustomerAggregate(AddCustomerCommand cmd) {
        log.info("AddCustomerCommand id: {}", cmd.getId());
        Assert.notNull(cmd.getId(), "Id should not be null");
        Assert.notNull(cmd.getName(), "Name should not be null");

        var customer = AddCustomerEvent.builder()
                .id(cmd.getId())
                .name(cmd.getName())
                .preference(cmd.getPreference())
                .build();

        AggregateLifecycle.apply(customer);
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand cmd) {
        log.info("UpdateCustomerCommand id: {}", cmd.getId());
        Assert.notNull(cmd.getId(), "Id should not be null");
        Assert.notNull(cmd.getPreference(), "preference should not be null");

        var customer = UpdateCustomerEvent.builder()
                .id(cmd.getId())
                .preference(cmd.getPreference())
                .build();

        AggregateLifecycle.apply(customer);
    }

    @CommandHandler
    public void handle(DeleteCustomerCommand command) {
        var event = DeleteCustomerEvent.builder()
                .id(command.getId())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeleteCustomerEvent event) {
        AggregateLifecycle.markDeleted();
    }

    @EventSourcingHandler
    private void on(AddCustomerEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.preference = event.getPreference();
    }

    @EventSourcingHandler
    private void on(UpdateCustomerEvent event) {
        this.id = event.getId();
        this.preference = event.getPreference();
    }

}
