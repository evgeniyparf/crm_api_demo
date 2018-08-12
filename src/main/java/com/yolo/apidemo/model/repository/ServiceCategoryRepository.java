package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.Service;
import com.yolo.apidemo.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
}
