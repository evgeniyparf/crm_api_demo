package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
