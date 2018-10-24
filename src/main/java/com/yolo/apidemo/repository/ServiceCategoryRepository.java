package com.yolo.apidemo.repository;

import com.yolo.apidemo.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
    List<ServiceCategory> findByTitleIgnoreCaseContaining(String title);
}
