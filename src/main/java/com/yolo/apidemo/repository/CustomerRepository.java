package com.yolo.apidemo.repository;

import com.yolo.apidemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
