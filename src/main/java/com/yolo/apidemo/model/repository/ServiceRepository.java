package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
