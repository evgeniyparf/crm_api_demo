package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, Integer> {
}
