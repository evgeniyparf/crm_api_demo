package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.ServiceStatus;
import com.yolo.apidemo.model.repository.ServiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
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
    public List<ServiceStatus> getAllServiceStatuses(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                     @RequestParam(name = "name", required = false) String name){
        if(name != null)
            return serviceStatusRepository.findByNameIgnoreCaseContaining(name);
        else
            return serviceStatusRepository.findAll();
    }

    @GetMapping("/service_statuses/{id}")
    public ServiceStatus getServiceStatus(@PathVariable int id){
        return serviceStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service status " + id + " not found"));
    }

    @PutMapping("/service_statuses/{id}")
    public HttpStatus updateServiceStatus(@PathVariable int id, @Valid @RequestBody ServiceStatus serviceStatusDetails){
        ServiceStatus serviceStatus = serviceStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service status " + id + " not found"));
        if(serviceStatusDetails.getName() != null)
            serviceStatus.setName(serviceStatusDetails.getName());
        serviceStatusRepository.save(serviceStatus);
        return HttpStatus.OK;
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
