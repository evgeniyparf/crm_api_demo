package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.ServiceCategory;
import com.yolo.apidemo.model.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceCategoryController {
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @GetMapping("/service_category")
    public List<ServiceCategory> getAllServiceCategories(){
        return serviceCategoryRepository.findAll();
    }

    @GetMapping("/service_category/{id}")
    public ServiceCategory getServiceCategory(@PathVariable int id){
        return serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service Category " + id + " not found"));
    }

    @PostMapping("/service_category")
    public ServiceCategory newServiceCategory(@Valid @RequestBody ServiceCategory serviceCategory){
        return serviceCategoryRepository.save(serviceCategory);
    }

    @DeleteMapping("/service_category/{id}")
    public ResponseEntity<?> deleteServiceCategory(@PathVariable int id){
        serviceCategoryRepository.delete(serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service Category " + id + "not found")));
        return ResponseEntity.ok().build();
    }
}
