package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;
    private String phone;
    private String email;
    private String notes;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ServiceHistory> serviceHistories;


    public Customer(){ }

    public Customer(String name, String surname, Date date_of_birth, String phone, String email, String notes) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }

    public Integer getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonIgnore
    public Set<ServiceHistory> getServiceHistories() {
        return serviceHistories;
    }

    public void setServiceHistories(Set<ServiceHistory> serviceHistories) {
        this.serviceHistories = serviceHistories;
    }
}
