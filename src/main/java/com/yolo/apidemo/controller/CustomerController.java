package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Customer;
import com.yolo.apidemo.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:3000")
//@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(@RequestParam(name = "name", required = false) String name,
                                          @RequestParam(name = "surname", required = false) String surname,
                                          @RequestParam(name = "email", required = false) String email,
                                          @RequestParam(name = "phone", required = false) String phone)
    {
        
        //return customerRepository.findByNameIgnoreCaseContainingOrSurnameIgnoreCaseContainingOrEmailOrPhone(name, surname, email, phone);
        //return customerRepository.findByNameIgnoreCaseContainingAndSurnameIgnoreCaseContainingAndEmailAndPhone(name,surname,email,phone);
                return customerRepository.findAll();
    }

    @GetMapping("/customers/fullname/{fullname}")
    public List<Customer> getCustomerByFullName(@PathVariable String fullname){
        String[] splitedFullName = fullname.split("\\+");
        String name = splitedFullName[0];
        String surname = splitedFullName[1];
        return customerRepository.findByNameIgnoreCaseContainingAndSurnameIgnoreCaseContaining(name, surname);
    }

    @GetMapping("/customers/name/{name}")
    public List<Customer> getCustomerByName(@PathVariable String name){
        return customerRepository.findByNameIgnoreCaseContaining(name);
    }

    @GetMapping("/customers/surname/{surname}")
    public List<Customer> getCustomerBySurname(@PathVariable String surname){
        return customerRepository.findBySurnameIgnoreCaseContaining(surname);
    }

    @GetMapping("/customers/email/{email}")
    public List<Customer> getCustomerByEmail(@PathVariable String email){
        return customerRepository.findByEmail(email);
    }

    @GetMapping("/customers/phone/{phone}")
    public List<Customer> getCustomerByPhone(@PathVariable String phone){
        return customerRepository.findByPhone(phone);
    }

    @PostMapping("/customers")
    public Customer newCustomer(@Valid @RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" + id + "not found"));
    }

    @PutMapping("/customers/{id}")
    public HttpStatus updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customerDetails){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" + id + "not found"));
        if(customerDetails.getName() != null)
            customer.setName(customerDetails.getName());
        if(customerDetails.getSurname() != null)
            customer.setSurname(customerDetails.getSurname());
        //System.out.println(customerDetails.getDate_of_birth());
        if(customerDetails.getDate_of_birth() != null)
            customer.setDate_of_birth(customerDetails.getDate_of_birth());
        if(customerDetails.getPhone() != null)
            customer.setPhone(customerDetails.getPhone());
        if(customerDetails.getNotes() != null)
            customer.setNotes(customerDetails.getNotes());
        customerRepository.save(customer);
        return HttpStatus.OK;
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id){
        customerRepository.delete(customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" + id + "not found")));
        return ResponseEntity.ok().build();
    }
}
