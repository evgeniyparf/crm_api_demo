package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "initial_price")
    private Integer initialPrice;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ServiceCategory serviceCategory;

    //time in seconds
    private Integer time;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private Set<ServiceHistory> serviceHistories;

    public Service() {
    }

    public Service(String name, int initialPrice, int price, ServiceCategory serviceCategory, int time) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.price = price;
        this.serviceCategory = serviceCategory;
        this.time = time;
    }

    public Integer getId() {
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

    public Integer getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @JsonIgnore
    public Set<ServiceHistory> getServiceHistories() {
        return serviceHistories;
    }

    public void setServiceHistories(Set<ServiceHistory> serviceHistories) {
        this.serviceHistories = serviceHistories;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialPrice=" + initialPrice +
                ", price=" + price +
                //", serviceCategory=" + serviceCategory +
                ", time=" + time +
                '}';
    }
}
