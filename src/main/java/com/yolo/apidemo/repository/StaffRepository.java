package com.yolo.apidemo.repository;

import com.yolo.apidemo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
