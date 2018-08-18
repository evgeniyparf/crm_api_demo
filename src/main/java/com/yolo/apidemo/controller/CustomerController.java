package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Customer;
import com.yolo.apidemo.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:3000")
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/customer")
    public Customer newCustomer(@Valid @RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" + id + "not found"));
    }

    @PutMapping("/customer/{id}")
    public HttpStatus updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customerDetails){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" + id + "not found"));
        customerRepository.save(customerDetails);
        return HttpStatus.OK;
    }

    @PatchMapping("/customer/{id}")
    public Customer patchCustomer(@PathVariable int id, @Valid @RequestBody Customer customerDetails){
        customerDetails.setId(id);
        return customerRepository.save(customerDetails);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id){
        customerRepository.delete(customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" + id + "not found")));
        return ResponseEntity.ok().build();
    }


}
