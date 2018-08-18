package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Service;
import com.yolo.apidemo.model.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/services")
    public List<Service> getAllServices(){
        return serviceRepository.findAll();
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
