package com.tarabut.customer.cmd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarabut.customer.cmd.commands.AddCustomerCommand;
import com.tarabut.customer.core.enums.Preference;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenValidCustomerInput_thenReturns200() throws Exception {
        AddCustomerCommand addCustomer = new AddCustomerCommand();
        addCustomer.setName("Customer");
        addCustomer.setPreference(Preference.EMAIL);

        when(commandGateway.sendAndWait(addCustomer)).thenReturn( addCustomer);

        mockMvc.perform(post("/api/v1/customers")
                        .contentType("application/json").content(objectMapper.writeValueAsString(addCustomer)))
                .andExpect(status().isCreated());

        verify(commandGateway , atLeast(1)).send(Mockito.any());

    }

}