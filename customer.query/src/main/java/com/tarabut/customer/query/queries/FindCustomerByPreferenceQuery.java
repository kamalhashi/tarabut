package com.tarabut.customer.query.queries;

import com.tarabut.customer.core.enums.Preference;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCustomerByPreferenceQuery {
    private Preference preference;
}
