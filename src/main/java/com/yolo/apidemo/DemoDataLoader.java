package com.yolo.apidemo;

import com.yolo.apidemo.builder.CustomerBuilder;
import com.yolo.apidemo.builder.ServiceBuilder;
import com.yolo.apidemo.model.ServiceCategory;
import com.yolo.apidemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class DemoDataLoader implements ApplicationRunner {

    private CustomerRepository customerRepository;
    private ServiceRepository serviceRepository;
    private ServiceCategoryRepository serviceCategoryRepository;
    private ServiceStatusRepository serviceStatusRepository;
    private ServiceHistoryRepository serviceHistoryRepository;

    @Autowired

    public DemoDataLoader(CustomerRepository customerRepository, ServiceRepository serviceRepository, ServiceCategoryRepository serviceCategoryRepository, ServiceStatusRepository serviceStatusRepository, ServiceHistoryRepository serviceHistoryRepository) {
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.serviceStatusRepository = serviceStatusRepository;
        this.serviceHistoryRepository = serviceHistoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerRepository.save(new CustomerBuilder()
                .setFirstName("Yevhenii")
                .setSecondName("Parfonov")
                .setThirdName("Yurievich")
                .setDateOfBirth(new Calendar.Builder().setDate(1998, 06, 06).build().getTime())
                .setEmail("yevhenii.parfonov@gmail.com")
                .setPhone("380957446020")
                .setNotes("some c00l gUy")
                .build());
        customerRepository.save(new CustomerBuilder()
                .setFirstName("Ivan")
                .setSecondName("Ivanov")
                .setThirdName("Ivanovich")
                .setDateOfBirth(new Calendar.Builder().setDate(1985, 10, 07).build().getTime())
                .setEmail("ivan.ivanov@gmail.com")
                .setPhone("380951234567")
                .setNotes("Ivan0v")
                .build());

        ServiceCategory sc1 = new ServiceCategory("Category 1");
        serviceCategoryRepository.save(sc1);
        ServiceCategory sc2 = new ServiceCategory("Category 2");
        serviceCategoryRepository.save(sc2);

        serviceRepository.save(new ServiceBuilder()
                .setTitle("Service 1")
                .setPrice(100)
                .setInitialPrice(150)
                .setServiceCategory(sc1)
                .setTime(3600)
                .build());

        serviceRepository.save(new ServiceBuilder()
                .setTitle("Service 2")
                .setPrice(500)
                .setInitialPrice(500)
                .setServiceCategory(sc2)
                .setTime(7200)
                .build());

        serviceRepository.save(new ServiceBuilder()
                .setTitle("Service 3")
                .setPrice(600)
                .setInitialPrice(850)
                .setServiceCategory(sc2)
                .setTime(3200)
                .build());
    }
}
