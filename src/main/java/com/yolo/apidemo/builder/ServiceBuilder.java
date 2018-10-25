package com.yolo.apidemo.builder;

import com.yolo.apidemo.model.ServiceCategory;

public class ServiceBuilder {

    private String title;
    private Integer initialPrice;
    private Integer price;
    private Integer time;
    private ServiceCategory serviceCategory;

    public ServiceBuilder() {
    }

    public String getTitle() {
        return title;
    }

    public ServiceBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getInitialPrice() {
        return initialPrice;
    }

    public ServiceBuilder setInitialPrice(Integer initialPrice) {
        this.initialPrice = initialPrice;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ServiceBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public ServiceBuilder setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public ServiceBuilder setTime(Integer time) {
        this.time = time;
        return this;
    }

    public com.yolo.apidemo.model.Service build() {
        return new com.yolo.apidemo.model.Service(title, initialPrice, price, serviceCategory, time);
    }
}
