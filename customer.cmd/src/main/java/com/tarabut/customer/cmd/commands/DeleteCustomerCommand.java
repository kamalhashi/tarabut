package com.tarabut.customer.cmd.commands;

import com.tarabut.customer.core.enums.Preference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteCustomerCommand {
    @TargetAggregateIdentifier
    private String id;
}
