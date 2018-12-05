package com.example.demo.Repository;

import com.example.demo.DemoApplicationTests;
import com.example.demo.Domain.Employee;
import com.example.demo.Service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryTest extends DemoApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testFindByName(){
        Employee employee = employeeService.findByName("zhangsan");
        System.out.println(
                "id:"+employee.getId()+
                ",name:"+employee.getName()+
                ",age:"+employee.getAge()
        );
    }

    @Test
    public void testFindAll(){
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeService.findAll();
        employees.forEach(p -> System.out.println(
                "id:"+p.getId()+ ",name:"+p.getName()+",age:"+p.getAge()
        ));
    }

    @Test
    public void testSave(){
        Employee employee = new Employee();
        employee.setName("lisi");
        employee.setAge(45);
        employeeService.save(employee);

    }


}
