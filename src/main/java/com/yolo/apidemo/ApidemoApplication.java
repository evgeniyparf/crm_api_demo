package com.yolo.apidemo;

import com.yolo.apidemo.model.Customer;
import com.yolo.apidemo.model.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ApidemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApidemoApplication.class, args);
    }
}
