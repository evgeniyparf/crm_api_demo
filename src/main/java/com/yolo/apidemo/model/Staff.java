package com.yolo.apidemo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer             id;
    private String              first_name;
    private String              second_name;
    private String              third_name;
    @ManyToMany
    @JoinTable(name = "position_staff",
            joinColumns = { @JoinColumn (name = "staff_id")},
            inverseJoinColumns = { @JoinColumn (name = "position_id")})
    private Set<StaffPosition>  staffPositions = new HashSet<>();
    private Date                dateOfBirth;
    private Date                dateAdded;

    public Staff() {
    }

    public Staff(String first_name, String second_name, String third_name, Date dateOfBirth) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.third_name = third_name;
        this.dateOfBirth = dateOfBirth;
        this.dateAdded = new Date();
    }

    public Integer getId() {
        return id;
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

    public String getThird_name() {
        return third_name;
    }

    public void setThird_name(String third_name) {
        this.third_name = third_name;
    }

    public Set<StaffPosition> getStaffPositions() {
        return staffPositions;
    }

    public void setStaffPositions(Set<StaffPosition> staffPositions) {
        this.staffPositions = staffPositions;
    }

    public void addStaffPosition(StaffPosition staffPosition) {
        staffPositions.add(staffPosition);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
}
