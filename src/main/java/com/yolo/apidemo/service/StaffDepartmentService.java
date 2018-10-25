package com.yolo.apidemo.service;

import com.yolo.apidemo.model.StaffDepartment;
import com.yolo.apidemo.repository.StaffDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffDepartmentService {
    @Autowired
    private StaffDepartmentRepository staffDepartmentRepository;

    public List<StaffDepartment> getAllStaffDepartments(int page, int size,
                                                        String title) {
        if(title != null)
            return staffDepartmentRepository.findByTitleIgnoreCaseContaining(title);
        else
            return staffDepartmentRepository.findAll(PageRequest.of(page,size)).getContent();
    }

    public StaffDepartment getStaffDepartmentById(int id) {
        return staffDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department id:" + id + " not found"));
    }

    public StaffDepartment updateStaffDepartment(int id, StaffDepartment staffDepartmentNew) {
        StaffDepartment staffDepartment = staffDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department id:" + id + " not found"));
        if(staffDepartmentNew.getTitle() != null) staffDepartment.setTitle(staffDepartmentNew.getTitle());
        return staffDepartmentRepository.save(staffDepartment);
    }

    public StaffDepartment createStaffDepartment(StaffDepartment staffDepartment) {
        return staffDepartmentRepository.save(staffDepartment);
    }

    public HttpStatus deleteStaffDepartment(int id) {
        staffDepartmentRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
