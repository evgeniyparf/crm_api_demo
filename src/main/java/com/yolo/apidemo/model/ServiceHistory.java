package com.yolo.apidemo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_histories")
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "history_service",
                joinColumns = { @JoinColumn (name = "history_id")},
                inverseJoinColumns = { @JoinColumn (name = "service_id")})
    private Set<Service> services = new HashSet<>();

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

    public ServiceHistory(Customer customer, Date dateAdded, Date dateProc){
        this.customer = customer;
        this.dateAdded = dateAdded;
        this.dateProc = dateProc;
    }

    public ServiceHistory(Set<Service> services, Customer customer, Date dateAdded, Date dateProc, ServiceStatus serviceStatus) {
        this.services = services;
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

    public Set<Service> getService() {
        return services;
    }

    public void setService(Set<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        services.add(service);
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
                ", service=" + services +
                ", customer=" + customer +
                ", dateAdded=" + dateAdded +
                ", dateProc=" + dateProc +
                ", serviceStatus=" + serviceStatus +
                '}';
    }
}
