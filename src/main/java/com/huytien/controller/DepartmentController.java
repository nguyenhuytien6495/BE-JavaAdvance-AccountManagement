package com.huytien.controller;

import com.huytien.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;




}
