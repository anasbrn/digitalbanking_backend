package org.digitalbanking.digitalbanking_backend.mappers;

import org.digitalbanking.digitalbanking_backend.dtos.CustomerDTO;
import org.digitalbanking.digitalbanking_backend.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    // can use mapStruct also
    public CustomerDTO toDto(Customer customer) {
        if (customer == null) return null;
        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        customerDTO.setEmail(customer.getEmail());
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) return null;
        Customer customer = new Customer();
//        customer.setId(customerDTO.getId());
//        customer.setName(customerDTO.getName());
//        customer.setEmail(customerDTO.getEmail());
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }
}
