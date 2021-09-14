package com.tarabut.customer.cmd.controllers;

import com.tarabut.customer.cmd.commands.AddCustomerCommand;
import com.tarabut.customer.cmd.commands.DeleteCustomerCommand;
import com.tarabut.customer.cmd.commands.UpdateCustomerCommand;
import com.tarabut.customer.core.dto.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
public class CustomerController {

    private final CommandGateway commandGateway;

    public CustomerController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody AddCustomerCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new CustomerResponse(id, "successfully added new customer!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var errorMessage = "Error while processing request to add new customer - " + id;
            log.info("Error:{}", e.toString());
            return new ResponseEntity<>(new CustomerResponse(id, errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable(value = "id") String id, @Valid @RequestBody UpdateCustomerCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command).get();
            return new ResponseEntity<>(new CustomerResponse("Updated customer successfully!" + id), HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error while processing request to update  customer - " + id;
            log.info("Error:{}", e.toString());
            return new ResponseEntity<>(new CustomerResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable(value = "id") String id) {
        try {
            var command = DeleteCustomerCommand.builder()
                    .id(id)
                    .build();

            commandGateway.send(command);

            return new ResponseEntity<>(new CustomerResponse("Customer deleted successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error while processing request to delete customer - " + id;
            log.info("Error:{}", e.toString());
            return new ResponseEntity<>(new CustomerResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
