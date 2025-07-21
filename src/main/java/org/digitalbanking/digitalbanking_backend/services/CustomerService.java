package org.digitalbanking.digitalbanking_backend.services;

import org.digitalbanking.digitalbanking_backend.dtos.CustomerDTO;

import java.util.List;

interface CustomerService {
    List<CustomerDTO> listCustomers();

    CustomerDTO findCustomerById(String id);

    CustomerDTO saveCustomer(CustomerDTO customer);

    void deleteCustomerById(String id);
}
