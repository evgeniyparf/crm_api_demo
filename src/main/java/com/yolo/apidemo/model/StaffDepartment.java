package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "staff_department")
public class StaffDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @OneToMany(mappedBy = "staffDepartment", cascade = CascadeType.ALL)
    private Set<StaffPosition> staffPosition;

    public StaffDepartment() {
    }

    public StaffDepartment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public Set<StaffPosition> getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(Set<StaffPosition> staffPosition) {
        this.staffPosition = staffPosition;
    }
}
