package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service_categories")
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @OneToMany(mappedBy = "serviceCategory", cascade = CascadeType.ALL)
    private Set<Service> services;

    public ServiceCategory() {

    }

    public ServiceCategory(String name) {
        this.title = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "ServiceCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", services=" + services +
                '}';
    }
}
