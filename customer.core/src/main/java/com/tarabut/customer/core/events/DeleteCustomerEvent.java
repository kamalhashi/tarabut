package com.tarabut.customer.core.events;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteCustomerEvent {
    @TargetAggregateIdentifier
    private String id;
}
