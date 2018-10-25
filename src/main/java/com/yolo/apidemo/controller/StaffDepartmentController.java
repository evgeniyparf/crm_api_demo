package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.StaffDepartment;
import com.yolo.apidemo.service.StaffDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StaffDepartmentController {

    @Autowired
    private StaffDepartmentService staffDepartmentService;

    @GetMapping("/staff_departments")
    public List<StaffDepartment> getAllStaffDepartments(@RequestParam(name = "page", required = false)   int     page,
                                                        @RequestParam(name = "size", required = false)   int     size,
                                                        @RequestParam(name = "title", required = false)  String  title) {
        return staffDepartmentService.getAllStaffDepartments(page, size, title);
    }

    @GetMapping("/staff_departments/{id}")
    public StaffDepartment getStaffDepartmentById(@PathVariable int id) {
        return staffDepartmentService.getStaffDepartmentById(id);
    }

    @PutMapping("/staff_departments/{id}")
    public StaffDepartment updateStaffDepartment(@PathVariable int id, @Valid @RequestBody StaffDepartment staffDepartment) {
        return staffDepartmentService.updateStaffDepartment(id, staffDepartment);
    }

    @PostMapping("/staff_departments")
    public StaffDepartment createStaffDepartment(@Valid @RequestBody StaffDepartment staffDepartment) {
        return staffDepartmentService.createStaffDepartment(staffDepartment);
    }

    @DeleteMapping("/staff_departments/{id}")
    public HttpStatus deleteStaffDepartment(@RequestParam int id) {
        return staffDepartmentService.deleteStaffDepartment(id);
    }
}
