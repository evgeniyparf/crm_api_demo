package com.yolo.apidemo.model.repository;

import com.yolo.apidemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByNameIgnoreCaseContainingAndSurnameIgnoreCaseContaining(String name, String surname);
    List<Customer> findByNameIgnoreCaseContaining(String name);
    List<Customer> findBySurnameIgnoreCaseContaining(String surname);
    List<Customer> findByEmail(String email);
    List<Customer> findByPhone(String phone);

}
