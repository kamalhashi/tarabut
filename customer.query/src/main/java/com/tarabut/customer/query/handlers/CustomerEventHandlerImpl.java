package com.tarabut.customer.query.handlers;

import com.tarabut.customer.core.events.AddCustomerEvent;
import com.tarabut.customer.core.events.DeleteCustomerEvent;
import com.tarabut.customer.core.events.UpdateCustomerEvent;
import com.tarabut.customer.query.entity.Customer;
import com.tarabut.customer.query.repositories.CustomerRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("tarabut-group")
public class CustomerEventHandlerImpl implements CustomerEventHandler {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerEventHandlerImpl(CustomerRepository accountRepository) {
        this.customerRepository = accountRepository;
    }

    @EventHandler
    @Override
    public void on(AddCustomerEvent event) {
        var customer = Customer.builder()
                .id(event.getId())
                .name(event.getName())
                .preference(event.getPreference())
                .build();

        customerRepository.save(customer);
    }

    @EventHandler
    @Override
    public void on(UpdateCustomerEvent event) {

        var customer = Customer.builder()
                .id(event.getId())
                .preference(event.getPreference())
                .build();
        customerRepository.save(customer);
    }

    @EventHandler
    @Override
    public void on(DeleteCustomerEvent event) {
        customerRepository.deleteById(event.getId());
    }

}
