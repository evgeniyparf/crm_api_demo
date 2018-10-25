package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "initial_price")
    private Integer initialPrice;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ServiceCategory serviceCategory;

    private Integer time;

    @ManyToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private Set<ServiceHistory> serviceHistories;

    public Service() {
    }

    public Service(String name, int initialPrice, int price, ServiceCategory serviceCategory, int time) {
        this.title = name;
        this.initialPrice = initialPrice;
        this.price = price;
        this.serviceCategory = serviceCategory;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Integer initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public void setTime(Integer time) {
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
        return "ServiceBuilder{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", initialPrice=" + initialPrice +
                ", price=" + price +
                //", serviceCategory=" + serviceCategory +
                ", time=" + time +
                '}';
    }
}
