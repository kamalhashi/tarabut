package com.tarabut.customer.query.repositories;

import com.tarabut.customer.core.enums.Preference;
import com.tarabut.customer.query.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    Optional<Customer> findById(String id);
    List<Customer> findByPreference(Preference preference);
}
