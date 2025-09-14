package com.sherif.Employees.System.controller;

import com.sherif.Employees.System.model.Employee;
import com.sherif.Employees.System.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public String viewHomePage(Model model){
        return showPage(1,model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee")Employee employee){
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id")long id, Model model){
        Employee employee=employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){
        this.employeeService.deleteEmployee(id);
        return "redirect:/";

    }
    @GetMapping("/page/{pageNo}")
    public String showPage(@PathVariable (value = "pageNo") int pageNo, Model model){
        int pageSize=5;
        Page<Employee> page = employeeService.findPaginated(pageNo,pageSize);
        List<Employee> employees=page.getContent();
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
//        model.addAttribute("pageSize",pageSize);
        model.addAttribute("listEmployees",employees);
        return "index";
    }


}
