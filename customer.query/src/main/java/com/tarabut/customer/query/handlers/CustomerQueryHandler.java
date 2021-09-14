package com.tarabut.customer.query.handlers;


import com.tarabut.customer.core.dto.CustomerResponse;
import com.tarabut.customer.query.queries.FindCustomerByPreferenceQuery;
import com.tarabut.customer.query.queries.FindCustomerByIdQuery;
import com.tarabut.customer.query.queries.FindAllCustomersQuery;

public interface CustomerQueryHandler {
    CustomerResponse findCustomerById(FindCustomerByIdQuery query);
    CustomerResponse findCustomerByPreference(FindCustomerByPreferenceQuery query);
    CustomerResponse findAllCustomers(FindAllCustomersQuery query);
}
