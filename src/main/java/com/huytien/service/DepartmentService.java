package com.huytien.service;

import com.huytien.entity.Department;
import com.huytien.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository repository;

    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentByID(int id) {
        return repository.findById(id).get();
    }

    @Override
    public Department getDepartmentByName(String name) {
//        return repository.findByName(name);
        return null;
    }

    @Override
    public void createDepartment(Department department) {
        repository.save(department);
    }

    @Override
    public void updateDepartment(int id, String newName) {
        Department department = repository.findById(id).get();
        department.setName(newName);
        repository.save(department);
    }

    @Override
    public void updateDepartment(Department department) {
        repository.save(department);
    }

    @Override
    public void deleteDepartment(int id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isDepartmentExistsByID(int id) {
        return repository.existsById(id);
    }

    @Override
    public boolean isDepartmentExistsByName(String name) {
//        return repository.existsByName(name);
        return false;
    }

}
