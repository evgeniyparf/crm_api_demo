package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Customer;
import com.yolo.apidemo.model.Service;
import com.yolo.apidemo.model.ServiceHistory;
import com.yolo.apidemo.model.ServiceStatus;
import com.yolo.apidemo.repository.CustomerRepository;
import com.yolo.apidemo.repository.ServiceHistoryRepository;
import com.yolo.apidemo.repository.ServiceRepository;
import com.yolo.apidemo.repository.ServiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api")
public class ServiceHistoryController {

    @Autowired
    private ServiceHistoryRepository serviceHistoryRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ServiceStatusRepository serviceStatusRepository;

    @GetMapping("/service_histories")
    public List<ServiceHistory> getAllServiceHistories(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                       @RequestParam(name = "service", required = false) Integer service,
                                                       @RequestParam(name = "customer", required = false) Integer customer,
                                                       @RequestParam(name = "dateAdded", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateAdded,
                                                       @RequestParam(name = "dateProc", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dateProc,
                                                       @RequestParam(name = "status", required = false) Integer status)
    {
        ServiceHistory serviceHistory = new ServiceHistory();
        if(service != null){
            Optional<Service> dbServiceCategory = serviceRepository.findById(service);
            if(dbServiceCategory.isPresent()) {
                Service existingServiceCategory = dbServiceCategory.get();
                serviceHistory.setService(existingServiceCategory);
            }
        }
        if(customer != null){
            Optional<Customer> dbCustomer = customerRepository.findById(customer);
            if(dbCustomer.isPresent()) {
                Customer existingCustomer = dbCustomer.get();
                serviceHistory.setCustomer(existingCustomer);
            }
        }
        if(dateAdded != null)
            serviceHistory.setDateAdded(dateAdded);
        if(dateProc != null)
            serviceHistory.setDateProc(dateProc);
        if(status != null){
            Optional<ServiceStatus> dbServiceStatus = serviceStatusRepository.findById(status);
            if(dbServiceStatus.isPresent()){
                ServiceStatus existingServiceStatus = dbServiceStatus.get();
                serviceHistory.setServiceStatus(existingServiceStatus);
            }
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<ServiceHistory> example = Example.of(serviceHistory, matcher);
        return serviceHistoryRepository.findAll(example, PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/service_histories/{id}")
    public ServiceHistory getServiceHistory(@PathVariable int id){
        return serviceHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service History " + id + " not found"));
    }

    @PutMapping("/service_histories/{id}")
    public HttpStatus updateServiceHistory(@PathVariable int id, @Valid @RequestBody ServiceHistory serviceHistoryDetails){
        ServiceHistory serviceHistory = serviceHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service history " + id + " not found"));
        if(serviceHistoryDetails.getService() != null)
            serviceHistory.setService(serviceHistoryDetails.getService());
        if(serviceHistoryDetails.getCustomer() != null)
            serviceHistory.setCustomer(serviceHistoryDetails.getCustomer());
        if(serviceHistoryDetails.getServiceStatus() != null)
            serviceHistory.setServiceStatus(serviceHistoryDetails.getServiceStatus());
        if(serviceHistoryDetails.getDateAdded() != null)
            serviceHistory.setDateAdded(serviceHistoryDetails.getDateAdded());
        if(serviceHistoryDetails.getDateProc() != null)
            serviceHistory.setDateProc(serviceHistoryDetails.getDateProc());
        serviceHistoryRepository.save(serviceHistory);
        return HttpStatus.OK;
    }

    @PostMapping("/service_histories")
    public ServiceHistory addServiceHistory(@Valid @RequestBody ServiceHistory serviceHistory){
        return serviceHistoryRepository.save(serviceHistory);
    }

    @DeleteMapping("/service_histories/{id}")
    public ResponseEntity<?> deleteServiceHistory(@PathVariable int id){
        serviceHistoryRepository.delete(serviceHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Service history " + id + " not found")));
        return ResponseEntity.ok().build();
    }
}
