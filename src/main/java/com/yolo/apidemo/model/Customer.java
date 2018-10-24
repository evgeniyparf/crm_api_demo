package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String first_name;
    private String second_name;
    private String third_name;
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;
    private String phone;
    private String email;
    private String notes;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ServiceHistory> serviceHistories;


    public Customer(){ }

    public Customer(String first_name, String second_name, String third_name, Date date_of_birth, String phone, String email, String notes) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.third_name = third_name;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setThird_name(String third_name) {
        this.third_name = third_name;
    }

    public String getThird_name() {
        return third_name;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
