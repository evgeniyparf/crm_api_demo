package com.yolo.apidemo.repository;

import com.yolo.apidemo.model.StaffDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface StaffDepartmentRepository extends JpaRepository<StaffDepartment, Integer> {
    public List<StaffDepartment> findByTitleIgnoreCaseContaining(String title);
}
