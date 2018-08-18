package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.ServiceHistory;
import com.yolo.apidemo.model.repository.ServiceHistoryRepository;
import com.yolo.apidemo.model.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class ServiceHistoryController {
    @Autowired
    private ServiceHistoryRepository serviceHistoryRepository;

    @GetMapping("/service_histories")
    public List<ServiceHistory> getAllServiceHistories(){
        return serviceHistoryRepository.findAll();
    }

    @GetMapping("/service_histories/{id}")
    public ServiceHistory getServiceHistory(@PathVariable int id){
        return serviceHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service History " + id + " not found"));
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
