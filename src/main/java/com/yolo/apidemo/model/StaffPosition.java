package com.yolo.apidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "staff_position")
public class StaffPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne
    private StaffDepartment staffDepartment;

    @ManyToMany(mappedBy = "staffPositions", cascade = CascadeType.ALL)
    private Set<Staff> staff;

    public StaffPosition() {
    }

    public StaffPosition(String title, StaffDepartment staffDepartment) {
        this.title = title;
        this.staffDepartment = staffDepartment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StaffDepartment getStaffDepartment() {
        return staffDepartment;
    }

    public void setStaffDepartment(StaffDepartment staffDepartment) {
        this.staffDepartment = staffDepartment;
    }

    @JsonIgnore
    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }
}
