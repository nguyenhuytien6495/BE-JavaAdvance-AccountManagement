package com.huytien.controller;

import com.huytien.dto.DepartmentDTO;
import com.huytien.entity.Department;
import com.huytien.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/departments")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> getAllDepartments() {
        List<Department> entityList = service.getAllDepartments();
        List<DepartmentDTO> dtoList = new ArrayList<>();

        for (Department entity: entityList){
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(entity.getId());
            departmentDTO.setName(entity.getName());
            dtoList.add(departmentDTO);
        }

        return new ResponseEntity<List<DepartmentDTO>>(dtoList, HttpStatus.OK);
    }

//    @GetMapping(value = "{/id}")
//    public ResponseEntity<?> getDepartmentById(@PathVariable int id) {
//        Department department = service.getDepartmentByID(id);
//        return new ResponseEntity<Department>(department, HttpStatus.OK);
//    }


}
