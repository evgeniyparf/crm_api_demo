package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ServiceStatusRepository extends JpaRepository<ServiceStatus, Integer> {
    List<ServiceStatus> findByNameIgnoreCaseContaining(String name);
}
