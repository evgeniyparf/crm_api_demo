package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service_status")
public class ServiceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status_name")
    private String name;

    @OneToMany(mappedBy = "serviceStatus", cascade = CascadeType.ALL)
    private Set<ServiceHistory> serviceHistory;

    public ServiceStatus(){

    }

    public ServiceStatus(String name, Set<ServiceHistory> serviceHistory) {
        this.name = name;
        this.serviceHistory = serviceHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<ServiceHistory> getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(Set<ServiceHistory> serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    @Override
    public String toString() {
        return "ServiceStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceHistory=" + serviceHistory +
                '}';
    }
}
