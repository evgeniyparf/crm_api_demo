package com.yolo.apidemo.repository;

import com.yolo.apidemo.model.StaffPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StaffPositionRepository extends JpaRepository<StaffPosition, Integer> {
}
