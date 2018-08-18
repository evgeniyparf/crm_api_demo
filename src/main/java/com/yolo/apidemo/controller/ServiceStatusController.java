package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.ServiceStatus;
import com.yolo.apidemo.model.repository.ServiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class ServiceStatusController {

    @Autowired
    private ServiceStatusRepository serviceStatusRepository;

    @GetMapping("/service_statuses")
    public List<ServiceStatus> getAllServiceStatuses(){
        return serviceStatusRepository.findAll();
    }

    @GetMapping("/service_statuses/{id}")
    public ServiceStatus getServiceStatus(@PathVariable int id){
        return serviceStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service status " + id + " not found"));
    }

    @PostMapping("/service_statuses")
    public ServiceStatus addServiceStatus(@Valid @RequestBody ServiceStatus serviceStatus){
        return serviceStatusRepository.save(serviceStatus);
    }

    @DeleteMapping("/service_statuses/{id}")
    public ResponseEntity<?> deleteServiceStatus(@PathVariable int id){
        serviceStatusRepository.delete(serviceStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Service status " + id + " not found")));
        return ResponseEntity.ok().build();
    }
}
