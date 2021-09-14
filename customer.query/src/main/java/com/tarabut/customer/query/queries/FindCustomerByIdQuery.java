package com.tarabut.customer.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCustomerByIdQuery {
    private String id;
}
