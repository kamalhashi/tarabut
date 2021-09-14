package com.tarabut.customer.core.events;

import com.tarabut.customer.core.enums.Preference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class AddCustomerEvent {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private Preference preference;
}
