package com.yolo.apidemo.service;

import com.yolo.apidemo.model.ServiceCategory;
import com.yolo.apidemo.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryService {
    
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public List<ServiceCategory> getAllServiceCategories(int page, int size, String title){
        if(title != null)
            return serviceCategoryRepository.findByTitleIgnoreCaseContaining(title);
        else
            return serviceCategoryRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public ServiceCategory getServiceCategory(int id){
        return serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ServiceBuilder Category " + id + " not found"));
    }

    public ServiceCategory updateServiceCategory(int id, ServiceCategory serviceCategoryDetails){
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found"));
        if(serviceCategoryDetails.getTitle() != null)
            serviceCategory.setTitle(serviceCategoryDetails.getTitle());
        serviceCategoryRepository.save(serviceCategory);
        return serviceCategory;
    }

    public ServiceCategory newServiceCategory(ServiceCategory serviceCategory){
        return serviceCategoryRepository.save(serviceCategory);
    }

    public HttpStatus deleteServiceCategory(int id){
        serviceCategoryRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
