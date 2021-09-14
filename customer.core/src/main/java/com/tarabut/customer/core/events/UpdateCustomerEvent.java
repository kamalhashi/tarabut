package com.tarabut.customer.core.events;

import com.tarabut.customer.core.enums.Preference;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateCustomerEvent {
    @TargetAggregateIdentifier
    private String id;
    private Preference preference;
}
