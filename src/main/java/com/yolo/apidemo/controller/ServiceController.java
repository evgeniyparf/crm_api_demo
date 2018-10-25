package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Service;
import com.yolo.apidemo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    public List<Service> getAllServices(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                        @RequestParam(name = "title", required = false) String title,
                                        @RequestParam(name = "category", required = false) Integer category,
                                        @RequestParam(name = "initialPrice", required = false) Integer initialPrice,
                                        @RequestParam(name = "price", required = false) Integer price,
                                        @RequestParam(name = "time", required = false) Integer time)
    {
        return serviceService.getAllServices(page, size, title, category, initialPrice, price, time);
    }

    @GetMapping("/services/{id}")
    public Service getService(@PathVariable int id){
        return serviceService.getService(id);
    }

    @PutMapping("/services/{id}")
    public Service updateService(@PathVariable int id, @Valid @RequestBody Service serviceDetails){
        return serviceService.updateService(id, serviceDetails);
    }

    @PostMapping("/services")
    public Service addService(@Valid @RequestBody Service service){
        return serviceService.addService(service);
    }

    @DeleteMapping("/services/{id}")
    public HttpStatus deleteCustomer(@PathVariable int id){
        return serviceService.deleteService(id);
    }
}
