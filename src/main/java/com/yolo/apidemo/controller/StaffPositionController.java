package com.yolo.apidemo.controller;

import com.yolo.apidemo.model.StaffPosition;
import com.yolo.apidemo.service.StaffPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StaffPositionController {

    @Autowired
    private StaffPositionService staffPositionService;

    @GetMapping("/staff_positions")
    public List<StaffPosition> getAllStaffPositions(@RequestParam(name = "page", required = false)   int       page,
                                                    @RequestParam(name = "size", required = false)   int       size,
                                                    @RequestParam(name = "title", required = false)   String    title,
                                                    @RequestParam(name = "department", required = false)   Integer   department) {
        return staffPositionService.getAllStaffPositions(page, size, title, department);
    }

    @GetMapping("/staff_positions/{id}")
    public StaffPosition getStaffPositionById(@PathVariable int id) {
        return staffPositionService.getStaffPositionById(id);
    }

    @PutMapping("/staff_positions/{id}")
    public StaffPosition updateStaffPositionById(@PathVariable int id, @Valid @RequestBody StaffPosition staffPosition) {
        return staffPositionService.updateStaffPosition(id, staffPosition);
    }

    @PostMapping("/staff_positions")
    public StaffPosition createStaffPosition(@Valid @RequestBody StaffPosition staffPosition) {
        return staffPositionService.createStaffPosition(staffPosition);
    }

    @DeleteMapping("/staff_positions/{id}")
    public HttpStatus deleteStaffPosition(@PathVariable int id) {
        return staffPositionService.deleteStaffPosition(id);
    }
}
