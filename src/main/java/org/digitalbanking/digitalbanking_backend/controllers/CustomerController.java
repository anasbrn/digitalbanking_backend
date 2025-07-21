package org.digitalbanking.digitalbanking_backend.controllers;

import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.CustomerDTO;
import org.digitalbanking.digitalbanking_backend.services.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerServiceImpl;

    @GetMapping("")
    public List<CustomerDTO> listCustomers() {
        return customerServiceImpl.listCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO findCustomerById(@PathVariable(name = "id") String id) {
        return customerServiceImpl.findCustomerById(id);
    }

    @PostMapping("")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerServiceImpl.saveCustomer(customerDTO);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable(name = "id") String id) {
        customerServiceImpl.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
