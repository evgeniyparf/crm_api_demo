package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Customer;
import com.yolo.apidemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(//Pagination data
                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                          @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                          //Customer Entity Data Fields
                                          @RequestParam(name = "first_name", required = false) String first_name,
                                          @RequestParam(name = "second_name", required = false) String second_name,
                                          @RequestParam(name = "third_name", required = false) String third_name,
                                          @RequestParam(name = "email", required = false) String email,
                                          @RequestParam(name = "phone", required = false) String phone)
    {
        return customerService.getCustomers(page, size, first_name, second_name, third_name, email, phone);
    }

    @PostMapping("/customers")
    public Customer newCustomer(@Valid @RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customerDetails){
        return customerService.updateCustomer(id, customerDetails);
    }

    @DeleteMapping("/customers/{id}")
    public HttpStatus deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }
}
