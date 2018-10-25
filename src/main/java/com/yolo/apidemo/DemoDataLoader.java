package com.yolo.apidemo;

import com.yolo.apidemo.builder.CustomerBuilder;
import com.yolo.apidemo.builder.ServiceBuilder;
import com.yolo.apidemo.model.*;
import com.yolo.apidemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DemoDataLoader implements ApplicationRunner {

    private CustomerRepository customerRepository;
    private ServiceRepository serviceRepository;
    private ServiceCategoryRepository serviceCategoryRepository;
    private ServiceStatusRepository serviceStatusRepository;
    private ServiceHistoryRepository serviceHistoryRepository;
    private StaffRepository staffRepository;
    private StaffPositionRepository staffPositionRepository;
    private StaffDepartmentRepository staffDepartmentRepository;

    @Autowired
    public DemoDataLoader(CustomerRepository customerRepository, ServiceRepository serviceRepository, ServiceCategoryRepository serviceCategoryRepository, ServiceStatusRepository serviceStatusRepository, ServiceHistoryRepository serviceHistoryRepository, StaffRepository staffRepository, StaffPositionRepository staffPositionRepository, StaffDepartmentRepository staffDepartmentRepository) {
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.serviceStatusRepository = serviceStatusRepository;
        this.serviceHistoryRepository = serviceHistoryRepository;
        this.staffRepository = staffRepository;
        this.staffPositionRepository = staffPositionRepository;
        this.staffDepartmentRepository = staffDepartmentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Customer c1 = new CustomerBuilder()
                .setFirstName("Yevhenii")
                .setSecondName("Parfonov")
                .setThirdName("Yurievich")
                .setDateOfBirth(new Calendar.Builder().setDate(1998, 6, 6).build().getTime())
                .setEmail("yevhenii.parfonov@gmail.com")
                .setPhone("380957446020")
                .setNotes("some c00l gUy")
                .build();
        customerRepository.save(c1);

        Customer c2 = new CustomerBuilder()
                .setFirstName("Ivan")
                .setSecondName("Ivanov")
                .setThirdName("Ivanovich")
                .setDateOfBirth(new Calendar.Builder().setDate(1985, 10, 7).build().getTime())
                .setEmail("ivan.ivanov@gmail.com")
                .setPhone("380951234567")
                .setNotes("Ivan0v")
                .build();
        customerRepository.save(c2);

        ServiceCategory sc1 = new ServiceCategory("Category 1");
        serviceCategoryRepository.save(sc1);
        ServiceCategory sc2 = new ServiceCategory("Category 2");
        serviceCategoryRepository.save(sc2);

        Service s1 = new ServiceBuilder()
                .setTitle("ServiceBuilder 1")
                .setPrice(100)
                .setInitialPrice(150)
                .setServiceCategory(sc1)
                .setTime(3600)
                .build();
        serviceRepository.save(s1);

        Service s2 = new ServiceBuilder()
                .setTitle("ServiceBuilder 2")
                .setPrice(500)
                .setInitialPrice(500)
                .setServiceCategory(sc2)
                .setTime(7200)
                .build();
        serviceRepository.save(s2);

        Service s3 = new ServiceBuilder()
                .setTitle("ServiceBuilder 3")
                .setPrice(600)
                .setInitialPrice(850)
                .setServiceCategory(sc2)
                .setTime(3200)
                .build();
        serviceRepository.save(s3);

        ServiceStatus st1 = new ServiceStatus("Added");
        ServiceStatus st2 = new ServiceStatus("Visited");
        ServiceStatus st3 = new ServiceStatus("Complete");
        serviceStatusRepository.save(st1);
        serviceStatusRepository.save(st2);
        serviceStatusRepository.save(st3);

        Set<Service> servicesSet1 = new HashSet<>();
        servicesSet1.add(s1);
        servicesSet1.add(s2);
        serviceHistoryRepository.save(new ServiceHistory(servicesSet1, c1, new Date(),
                new Calendar.Builder().setDate(2018, 10, 28).setTimeOfDay(14, 30, 0).build().getTime(),
                st1));

        Set<Service> servicesSet2 = new HashSet<>();
        servicesSet2.add(s2);
        servicesSet2.add(s3);
        serviceHistoryRepository.save(new ServiceHistory(servicesSet2, c2, new Date(),
                new Calendar.Builder().setDate(2018, 10, 29).setTimeOfDay(12, 0, 0).build().getTime(),
                st2));

        StaffDepartment sd1 = new StaffDepartment("Department 1");
        StaffDepartment sd2 = new StaffDepartment("Department 2");
        StaffDepartment sd3 = new StaffDepartment("Department 3");
        staffDepartmentRepository.save(sd1);
        staffDepartmentRepository.save(sd2);
        staffDepartmentRepository.save(sd3);

        StaffPosition sp1 = new StaffPosition("Парикмахер", sd1);
        StaffPosition sp2 = new StaffPosition("Менеджер", sd2);
        StaffPosition sp3 = new StaffPosition("Начальник отдела продаж", sd3);

        staffPositionRepository.save(sp1);
        staffPositionRepository.save(sp2);
        staffPositionRepository.save(sp3);

        Staff staff1 = new Staff("Ivan", "Ivanov", "Ivanovich",
                new Calendar.Builder().setDate(1998, 6, 6).build().getTime());
        staff1.addStaffPosition(sp2);

        Staff staff2 = new Staff("Olga", "Olgovich", "Olgovna",
                new Calendar.Builder().setDate(1980, 3, 2).build().getTime());
        staff2.addStaffPosition(sp1);

        Staff staff3 = new Staff("Iosiff", "Stalin", "Visarionovich",
                new Calendar.Builder().setDate(1896, 10, 3).build().getTime());
        staff3.addStaffPosition(sp3);

        staffRepository.save(staff1);
        staffRepository.save(staff2);
        staffRepository.save(staff3);
    }
}
