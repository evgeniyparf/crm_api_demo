package com.yolo.apidemo.service;

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

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    /*
    To do:
        - Добавить исключение если категории не существует
     */
    public List<com.yolo.apidemo.model.Service> getAllServices(int page, int size, String title, Integer category, Integer initialPrice, Integer price, Integer time) {
        Example<com.yolo.apidemo.model.Service> example = findServiceByExample(title, category, initialPrice, price, time);
        return serviceRepository.findAll(example, PageRequest.of(page, size)).getContent();
    }

    public com.yolo.apidemo.model.Service getService(int id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ServiceBuilder " + id + " not found"));
    }

    public com.yolo.apidemo.model.Service updateService(int id, com.yolo.apidemo.model.Service serviceNewInfo) {
        com.yolo.apidemo.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ServiceBuilder " + id + " not found"));
        if(serviceNewInfo.getTitle() != null)
            service.setTitle(serviceNewInfo.getTitle());
        if(serviceNewInfo.getInitialPrice() != null && serviceNewInfo.getInitialPrice() != 0)
            service.setInitialPrice(serviceNewInfo.getInitialPrice());
        if(serviceNewInfo.getPrice() != null && serviceNewInfo.getInitialPrice() != 0)
            service.setPrice(serviceNewInfo.getPrice());
        if(serviceNewInfo.getServiceCategory() != null)
            service.setServiceCategory(serviceNewInfo.getServiceCategory());
        serviceRepository.save(service);
        return service;
    }

    public com.yolo.apidemo.model.Service addService(com.yolo.apidemo.model.Service service) {
        return serviceRepository.save(service);
    }

    public HttpStatus deleteService(int id) {
        serviceRepository.delete(serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ServiceBuilder " + id + " not found")));
        return HttpStatus.NO_CONTENT;
    }

    private Example findServiceByExample(String title, Integer category, Integer initialPrice, Integer price, Integer time) {
        Service service = new Service();
        if(title != null)
            service.setTitle(title);
        if(initialPrice != null)
            service.setInitialPrice(initialPrice);
        if(price != null && time != 0)
            service.setPrice(price);
        if(time != null && time != 0)
            service.setTime(time);
        if(category != null) {
            Optional<ServiceCategory> serviceCategoryOptional = serviceCategoryRepository.findById(category);
            if(serviceCategoryOptional.isPresent())
                service.setServiceCategory(serviceCategoryOptional.get());
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        return Example.of(service, matcher);
    }
}
