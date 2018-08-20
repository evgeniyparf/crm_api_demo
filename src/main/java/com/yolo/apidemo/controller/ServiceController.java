package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Service;
import com.yolo.apidemo.model.repository.ServiceCategoryRepository;
import com.yolo.apidemo.model.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @GetMapping("/services")
    public List<Service> getAllServices(@RequestParam(name = "name", required = false) String name,
                                        @RequestParam(name = "categoryId", required = false) Integer categoryId,
                                        @RequestParam(name = "initialPrice", required = false) Integer initialPrice,
                                        @RequestParam(name = "price", required = false) Integer price,
                                        @RequestParam(name = "time", required = false) Integer time)
    {
        Service service = new Service();
        if(name != null)
            service.setName(name);
        if(categoryId != null && categoryId != 0)
            service.setServiceCategory(serviceCategoryRepository.findById(categoryId).get());
        if(initialPrice != null && initialPrice != 0)
            service.setInitialPrice(initialPrice);
        if(price != null && price != 0)
            service.setPrice(price);
        if(time != null && price != 0)
            service.setTime(time);

        System.out.println(service);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<Service> example = Example.of(service, matcher);
        System.out.println(example);
        return serviceRepository.findAll(example);
    }

    @GetMapping("/services/{id}")
    public Service getService(@PathVariable int id){
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service " + id + " not found"));
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
