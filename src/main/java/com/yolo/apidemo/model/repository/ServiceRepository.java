package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
