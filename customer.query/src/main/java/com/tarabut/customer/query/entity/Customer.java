package com.tarabut.customer.query.entity;

import com.tarabut.customer.core.enums.Preference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Customer {
    @Id
    private String id;
    private String name;
    private Preference preference;
}
