package com.yolo.apidemo.service;

import com.yolo.apidemo.model.StaffPosition;
import com.yolo.apidemo.repository.StaffDepartmentRepository;
import com.yolo.apidemo.repository.StaffPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffPositionService {

    @Autowired
    StaffPositionRepository staffPositionRepository;
    @Autowired
    StaffDepartmentRepository staffDepartmentRepository;

    public List<StaffPosition> getAllStaffPositions(int page, int size,
                                                    String title, Integer department) {
        Example example = getStaffPositionsByExample(title, department);
        return staffPositionRepository.findAll(example, PageRequest.of(page, size)).getContent();
    }

    public StaffPosition getStaffPositionById(int id) {
        return staffPositionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position id:" + id + " not found"));
    }

    public StaffPosition updateStaffPosition(int id, StaffPosition staffPositionNew) {
        StaffPosition staffPosition = new StaffPosition();
        if(staffPositionNew.getTitle() != null) staffPosition.setTitle(staffPositionNew.getTitle());
        if(staffPositionNew.getStaffDepartment() != null) staffPosition.setStaffDepartment(staffPositionNew.getStaffDepartment());
        return staffPositionRepository.save(staffPosition);
    }

    public StaffPosition createStaffPosition(StaffPosition staffPosition) {
        return staffPositionRepository.save(staffPosition);
    }

    public HttpStatus deleteStaffPosition(int id) {
        staffPositionRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

    private Example getStaffPositionsByExample(String title, Integer department) {
        StaffPosition staffPosition = new StaffPosition();
        if(title != null) staffPosition.setTitle(title);
        if(department != null) staffPosition.setStaffDepartment(staffDepartmentRepository.getOne(department));
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        return Example.of(staffPosition, exampleMatcher);
    }
}
