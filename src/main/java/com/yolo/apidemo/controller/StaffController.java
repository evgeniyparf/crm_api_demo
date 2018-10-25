package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.Staff;
import com.yolo.apidemo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/staff")
    public List<Staff> getAllStaff(@RequestParam(name = "page", required = false) int page,
                                   @RequestParam(name = "size", required = false) int size,
                                   @RequestParam(name = "first_name", required = false) String first_name,
                                   @RequestParam(name = "second_name", required = false) String second_name,
                                   @RequestParam(name = "third_name", required = false) String third_name,
                                   @RequestParam(name = "date_of_birth", required = false) Date date_of_birth,
                                   @RequestParam(name = "position", required = false) Integer position) {
        return staffService.getAllStaff(page, size, first_name, second_name, third_name, position, date_of_birth);
    }

    @GetMapping("/staff/{id}")
    public Staff getStaffById(@PathVariable int id){
        return staffService.getStaff(id);
    }

    @PutMapping("/staff/{id}")
    public Staff updateStaff(@PathVariable int id, @Valid @RequestBody Staff staff) {
        return staffService.updateStaff(id, staff);
    }

    @PostMapping("/staff")
    public Staff createStaff(@Valid @RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    @DeleteMapping("/staff/{id}")
    public HttpStatus deleteStaff(@PathVariable int id) {
        return staffService.deleteStaff(id);
    }
}
