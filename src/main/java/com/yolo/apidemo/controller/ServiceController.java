package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Service;
import com.yolo.apidemo.model.ServiceCategory;
import com.yolo.apidemo.repository.ServiceCategoryRepository;
import com.yolo.apidemo.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @GetMapping("/services")
    public List<Service> getAllServices(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                        @RequestParam(name = "name", required = false) String name,
                                        @RequestParam(name = "category", required = false) Integer category,
                                        @RequestParam(name = "initialPrice", required = false) Integer initialPrice,
                                        @RequestParam(name = "price", required = false) Integer price,
                                        @RequestParam(name = "time", required = false) Integer time)
    {
        Service service = new Service();
        if(name != null)
            service.setTitle(name);
        if(category != null){
            Optional<ServiceCategory> dbServiceCategory = serviceCategoryRepository.findById(category);
            if(dbServiceCategory.isPresent()){
                ServiceCategory existingServiceCategory = dbServiceCategory.get();
                service.setServiceCategory(existingServiceCategory);
            }
        }
        if(initialPrice != null && initialPrice != 0)
            service.setInitialPrice(initialPrice);
        if(price != null && price != 0)
            service.setPrice(price);
        if(time != null && time !=0)
            service.setTime(time);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<Service> example = Example.of(service, matcher);
        System.out.println(example);
        return serviceRepository.findAll(example, PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/services/{id}")
    public Service getService(@PathVariable int id){
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service " + id + " not found"));
    }

    @PutMapping("/services/{id}")
    public HttpStatus updateService(@PathVariable int id, @Valid @RequestBody Service serviceDetails){
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service " + id + " not found"));
        if(serviceDetails.getTitle() != null)
            service.setTitle(serviceDetails.getTitle());
        if(serviceDetails.getInitialPrice() != null && serviceDetails.getInitialPrice() != 0)
            service.setInitialPrice(serviceDetails.getInitialPrice());
        if(serviceDetails.getPrice() != null && serviceDetails.getInitialPrice() != 0)
            service.setPrice(serviceDetails.getPrice());
        if(serviceDetails.getServiceCategory() != null)
            service.setServiceCategory(serviceDetails.getServiceCategory());
        serviceRepository.save(service);
        return HttpStatus.OK;
    }

    @PostMapping("/services")
    public Service addService(@Valid @RequestBody Service service){
        return serviceRepository.save(service);
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id){
        serviceRepository.delete(serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Service " + id + " not found")));
        return ResponseEntity.ok().build();
    }
}
