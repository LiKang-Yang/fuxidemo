package com.example.demo.Service;

import com.example.demo.Domain.Employee;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findByName(String name){
        Employee employee = employeeRepository.findByName(name);
        return employee;
    }

    public List<Employee> findAll(){
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeRepository.findAll();
        return employees;
    }


    public Page<Employee> findAllByPage(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

}
