package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.ServiceCategory;
import com.yolo.apidemo.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class ServiceCategoryController {
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @GetMapping("/service_categories")
    public List<ServiceCategory> getAllServiceCategories(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                         @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                         @RequestParam(name = "name", required = false) String name){
        if(name != null)
            return serviceCategoryRepository.findByTitleIgnoreCaseContaining(name);
        else
            return serviceCategoryRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/service_categories/{id}")
    public ServiceCategory getServiceCategory(@PathVariable int id){
        return serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ServiceBuilder Category " + id + " not found"));
    }

    @PutMapping("/service_categories/{id}")
    public HttpStatus updateServiceCategory(@PathVariable int id, @Valid @RequestBody ServiceCategory serviceCategoryDetails){
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found"));
        if(serviceCategoryDetails.getTitle() != null)
            serviceCategory.setTitle(serviceCategoryDetails.getTitle());
        serviceCategoryRepository.save(serviceCategory);
        return HttpStatus.OK;
    }

    @PostMapping("/service_categories")
    public ServiceCategory newServiceCategory(@Valid @RequestBody ServiceCategory serviceCategory){
        return serviceCategoryRepository.save(serviceCategory);
    }

    @DeleteMapping("/service_categories/{id}")
    public ResponseEntity<?> deleteServiceCategory(@PathVariable int id){
        serviceCategoryRepository.delete(serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ServiceBuilder Category " + id + "not found")));
        return ResponseEntity.ok().build();
    }
}
