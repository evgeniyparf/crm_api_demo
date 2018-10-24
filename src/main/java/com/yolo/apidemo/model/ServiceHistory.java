package com.yolo.apidemo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "service_histories")
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_added")
    private Date dateAdded;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "date")
    private Date dateProc;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ServiceStatus serviceStatus;

    public ServiceHistory(){}

    public ServiceHistory(Service service, Customer customer, Date dateAdded, Date dateProc, ServiceStatus serviceStatus) {
        this.service = service;
        this.customer = customer;
        this.dateAdded = dateAdded;
        this.dateProc = dateProc;
        this.serviceStatus = serviceStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateProc() {
        return dateProc;
    }

    public void setDateProc(Date dateProc) {
        this.dateProc = dateProc;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    @Override
    public String toString() {
        return "ServiceHistory{" +
                "id=" + id +
                ", service=" + service +
                ", customer=" + customer +
                ", dateAdded=" + dateAdded +
                ", dateProc=" + dateProc +
                ", serviceStatus=" + serviceStatus +
                '}';
    }
}
