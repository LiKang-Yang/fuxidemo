package com.example.demo.Repository;

import com.example.demo.Domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee,Integer> {

    Page<Employee> findAll(Pageable pageable);

    Employee findByName(String name);

    List<Employee> findAll();
}
