package com.yolo.apidemo.builder;

import com.yolo.apidemo.model.Customer;

import java.util.Date;

public class CustomerBuilder {

    private String first_name;
    private String second_name;
    private String third_name;
    private Date date_of_birth;
    private String phone;
    private String email;
    private String notes;

    public CustomerBuilder() {
    }

    public CustomerBuilder setFirstName(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public CustomerBuilder setSecondName(String second_name) {
        this.second_name = second_name;
        return this;
    }

    public CustomerBuilder setThirdName(String third_name) {
        this.third_name = third_name;
        return this;
    }

    public CustomerBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Customer build(){
        return new Customer(first_name, second_name, third_name,
                            date_of_birth, phone, email, notes);
    }
}
