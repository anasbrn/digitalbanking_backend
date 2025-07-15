package org.digitalbanking.digitalbanking_backend.repositories;

import org.digitalbanking.digitalbanking_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
