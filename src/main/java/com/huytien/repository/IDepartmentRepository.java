package com.huytien.repository;

import com.huytien.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;


@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaRepositoryImplementation<Department, Integer> {

}
