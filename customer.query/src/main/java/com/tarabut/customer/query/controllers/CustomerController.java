package com.tarabut.customer.query.controllers;


import com.tarabut.customer.core.dto.CustomerResponse;
import com.tarabut.customer.query.queries.FindAllCustomersQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/customers")
@Slf4j
public class CustomerController {
    private final QueryGateway queryGateway;

    @Autowired
    public CustomerController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getAllCustomers() {
        try {
            var query = new FindAllCustomersQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(CustomerResponse.class)).join();

            if (response == null || response.getMessage() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Failed to get all customers";
            log.info("Error:{}", e.toString());
            return new ResponseEntity<>(new CustomerResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
