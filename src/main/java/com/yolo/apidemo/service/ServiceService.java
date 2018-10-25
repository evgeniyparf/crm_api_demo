package com.yolo.apidemo.service;

import com.yolo.apidemo.builder.ServiceBuilder;
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
    public List<Service> getAllServices(int page, int size, String title, Integer category, Integer initialPrice, Integer price, Integer time) {
        Example<Service> example = findServiceByExample(title, category, initialPrice, price, time);
        return serviceRepository.findAll(example, PageRequest.of(page, size)).getContent();
    }

    public Service getService(int id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service " + id + " not found"));
    }

    public Service updateService(int id, Service serviceNewInfo) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service " + id + " not found"));
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

    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    public HttpStatus deleteService(int id) {
        serviceRepository.delete(serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Service " + id + " not found")));
        return HttpStatus.NO_CONTENT;
    }

    private Example findServiceByExample(String title, Integer category, Integer initialPrice, Integer price, Integer time) {
        ServiceBuilder serviceBuilder = new ServiceBuilder();
        if(title != null)
            serviceBuilder.setTitle(title);
        if(initialPrice != null)
            serviceBuilder.setInitialPrice(initialPrice);
        if(price != null && time != 0)
            serviceBuilder.setPrice(price);
        if(time != null && time != 0)
            serviceBuilder.setTime(time);
        if(category != null) {
            Optional<ServiceCategory> serviceCategoryOptional = serviceCategoryRepository.findById(category);
            if(serviceCategoryOptional.isPresent())
                serviceBuilder.setServiceCategory(serviceCategoryOptional.get());
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        return Example.of(serviceBuilder.build(), matcher);
    }
}
