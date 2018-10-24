package com.yolo.apidemo.service;

import com.yolo.apidemo.model.Customer;
import com.yolo.apidemo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    private final static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers(int page, int size,
                                       String first_name, String second_name, String third_name, String email, String phone) {

        Example<Customer> example = findCustomersByExample(first_name, second_name, third_name, email, phone);
        return customerRepository.findAll(example, PageRequest.of(page, size)).getContent();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                //Добавить эксепшн
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    public Customer updateCustomer(int id, Customer customerNewInfo) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        if(customerNewInfo.getFirst_name() != null) customer.setFirst_name(customerNewInfo.getFirst_name());
        if(customerNewInfo.getSecond_name() != null) customer.setSecond_name(customerNewInfo.getSecond_name());
        if(customerNewInfo.getEmail() != null) customer.setEmail(customerNewInfo.getEmail());
        if(customerNewInfo.getPhone() != null) customer.setPhone(customerNewInfo.getPhone());
        if(customerNewInfo.getNotes() != null) customer.setNotes(customerNewInfo.getNotes());
        return customerRepository.save(customer);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public HttpStatus deleteCustomer(int id) {
        customerRepository.delete(customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException()));
        return HttpStatus.NO_CONTENT;
    }

    public Example findCustomersByExample(String first_name, String second_name, String third_name, String email, String phone) {
        Customer customer = new Customer();
        if(first_name != null) customer.setFirst_name(first_name);
        if(second_name != null) customer.setSecond_name(second_name);
        if(third_name != null) customer.setThird_name(third_name);
        if(email != null) customer.setEmail(email);
        if(phone != null) customer.setPhone(phone);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        return Example.of(customer, exampleMatcher);
    }
}
