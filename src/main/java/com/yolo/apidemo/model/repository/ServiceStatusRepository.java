package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceStatusRepository extends JpaRepository<ServiceStatus, Integer> {
}
