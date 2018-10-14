package com.example.demo.Controller;


import com.example.demo.Domain.Employee;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /*@RequestMapping(method = RequestMethod.GET)
    public String getEmployeeList(ModelMap map){
        map.addAttribute("empList",employeeService.findAll());
        return "employee/employeeIndex";
    }*/


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getEmployeeListByPage(
            @RequestParam(value="start",defaultValue = "0") Integer start,
            @RequestParam(value = "limit",defaultValue = "2") Integer limit
    ){
        start = start < 0 ? 0 :start;
        Sort sort =  new Sort(Sort.DEFAULT_DIRECTION,"id");
        Pageable pagerable = new PageRequest(start,limit,sort);
        Page<Employee> page = employeeService.findAllByPage(pagerable);

        ModelAndView mv = new ModelAndView("employee/employeeIndex");
        mv.addObject("page",page);
        return mv;
    }
}
