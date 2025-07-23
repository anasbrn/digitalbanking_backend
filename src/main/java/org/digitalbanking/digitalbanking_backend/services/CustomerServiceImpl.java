package org.digitalbanking.digitalbanking_backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.digitalbanking.digitalbanking_backend.dtos.CustomerDTO;
import org.digitalbanking.digitalbanking_backend.entities.Customer;
import org.digitalbanking.digitalbanking_backend.exceptions.CustomerNotFoundException;
import org.digitalbanking.digitalbanking_backend.mappers.CustomerMapper;
import org.digitalbanking.digitalbanking_backend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> customerMapper.toDto(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id: " + id + " not found")
        );
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDto) {
        customerDto.setId(UUID.randomUUID().toString());
        log.info("Saving customer {}", customerDto);
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public void deleteCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id: " + id + " not found")
        );
        log.info("Deleting customer by id success {}", id);
        customerRepository.delete(customer);
    }
}
