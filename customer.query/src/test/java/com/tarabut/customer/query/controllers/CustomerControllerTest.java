package com.tarabut.customer.query.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarabut.customer.core.dto.CustomerResponse;
import com.tarabut.customer.query.entity.Customer;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private  QueryGateway queryGateway;



    @Test
    public void getAllCustomers_thenReturns200() throws Exception {
        when(queryGateway.query(any(), eq(ResponseTypes.instanceOf(CustomerResponse.class)))).thenReturn( dummyCustomers());
        mockMvc.perform(get("/api/v1/customers")
                        .contentType("application/json"))
                .andExpect(status().isOk());

        verify(queryGateway , atLeast(1)).query(any(),eq(ResponseTypes.instanceOf(CustomerResponse.class)));

    }


    private CompletableFuture<CustomerResponse> dummyCustomers(){
        CustomerResponse<Customer> customerResponse = new CustomerResponse ("List of customer found",List.of(Customer.builder().name("Customer").build()));
        return  CompletableFuture.completedFuture(customerResponse);
    }


}