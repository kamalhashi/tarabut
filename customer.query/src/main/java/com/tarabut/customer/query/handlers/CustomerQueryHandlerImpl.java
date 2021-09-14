package com.tarabut.customer.query.handlers;

import com.tarabut.customer.core.dto.CustomerResponse;
import com.tarabut.customer.query.entity.Customer;
import com.tarabut.customer.query.queries.FindCustomerByPreferenceQuery;
import com.tarabut.customer.query.queries.FindAllCustomersQuery;
import com.tarabut.customer.query.queries.FindCustomerByIdQuery;
import com.tarabut.customer.query.repositories.CustomerRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerQueryHandlerImpl implements CustomerQueryHandler {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerQueryHandlerImpl(CustomerRepository accountRepository) {
        this.customerRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public CustomerResponse findCustomerById(FindCustomerByIdQuery query) {
        var customer = customerRepository.findById(query.getId());
        var response = customer.map(value -> new CustomerResponse("Bank Account Successfully Returned!", value.getId())).orElseGet(() -> new CustomerResponse("No Bank Account Found for ID - " + query.getId()));
        return response;
    }

    @QueryHandler
    @Override
    public CustomerResponse findCustomerByPreference(FindCustomerByPreferenceQuery query) {
        var customer = customerRepository.findByPreference(query.getPreference());
        var response = customer.isEmpty()
                ? new CustomerResponse("Preference  Successfully Returned!", query.getPreference().name())
                : new CustomerResponse("No Bank Account Found for Holder ID - " + query.getPreference().name());
        return response;
    }

    @QueryHandler
    @Override
    public CustomerResponse findAllCustomers(FindAllCustomersQuery query) {
        var customerIterator = customerRepository.findAll();

        if (!customerIterator.iterator().hasNext())
            return new CustomerResponse("No customers were Found!");

        var customers = new ArrayList<Customer>();
        customerIterator.forEach(i -> customers.add(i));
        var count = customers.size();

        return new CustomerResponse("Successfully Returned " + count + " Bank Account(s)!", customers);
    }

}
