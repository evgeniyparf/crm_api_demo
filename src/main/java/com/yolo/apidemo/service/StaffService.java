package com.yolo.apidemo.service;

import com.yolo.apidemo.model.Staff;
import com.yolo.apidemo.model.StaffPosition;
import com.yolo.apidemo.repository.StaffPositionRepository;
import com.yolo.apidemo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffPositionRepository staffPositionRepository;

    public List<Staff> getAllStaff(int page, int size,
                                   String first_name, String second_name, String third_name,
                                   Integer position, Date dateOfBirth) {
        Example example = findStaffByExample(first_name, second_name, third_name, position, dateOfBirth);
        return staffRepository.findAll(example, PageRequest.of(page, size)).getContent();
    }

    public Staff getStaff(int id){
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff id:" + id + " not found"));
    }

    public Staff updateStaff(int id, Staff staffNewInfo) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff id:" + id + " not found"));
        if(staffNewInfo.getFirst_name() != null) staff.setFirst_name(staffNewInfo.getFirst_name());
        if(staffNewInfo.getSecond_name() != null) staff.setSecond_name(staffNewInfo.getSecond_name());
        if(staffNewInfo.getThird_name() != null) staff.setThird_name(staffNewInfo.getThird_name());
        if(staffNewInfo.getDateOfBirth() != null) staff.setDateOfBirth(staffNewInfo.getDateOfBirth());
        if(staffNewInfo.getStaffPositions() != null) staff.setStaffPositions(staffNewInfo.getStaffPositions());
        return staffRepository.save(staff);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public HttpStatus deleteStaff(int id) {
        staffRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

    private Example findStaffByExample(String first_name, String second_name, String third_name, Integer position, Date dateOfBirth) {
        Staff staff = new Staff();
        if(first_name != null) staff.setFirst_name(first_name);
        if(second_name != null) staff.setSecond_name(second_name);
        if(third_name != null) staff.setThird_name(third_name);
        if(position != null) {
            Optional<StaffPosition> optionalStaff = staffPositionRepository.findById(position);
            if(optionalStaff.isPresent()) {
                StaffPosition existingStaff = optionalStaff.get();
                staff.addStaffPosition(existingStaff);
            }
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        return Example.of(staff, matcher);
    }

}
