package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.ServiceCategory;
import com.yolo.apidemo.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class ServiceCategoryController {

    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @GetMapping("/service_categories")
    public List<ServiceCategory> getAllServiceCategories(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                         @RequestParam(name = "size", required = false, defaultValue = "20") int size,
                                                         @RequestParam(name = "title", required = false) String title){
        return serviceCategoryService.getAllServiceCategories(page, size, title);
    }

    @GetMapping("/service_categories/{id}")
    public ServiceCategory getServiceCategory(@PathVariable int id){
        return serviceCategoryService.getServiceCategory(id);
    }

    @PutMapping("/service_categories/{id}")
    public ServiceCategory updateServiceCategory(@PathVariable int id, @Valid @RequestBody ServiceCategory serviceCategoryDetails){
        return serviceCategoryService.updateServiceCategory(id, serviceCategoryDetails);
    }

    @PostMapping("/service_categories")
    public ServiceCategory newServiceCategory(@Valid @RequestBody ServiceCategory serviceCategory){
        return serviceCategoryService.newServiceCategory(serviceCategory);
    }

    @DeleteMapping("/service_categories/{id}")
    public HttpStatus deleteServiceCategory(@PathVariable int id){
        return serviceCategoryService.deleteServiceCategory(id);
    }
}
