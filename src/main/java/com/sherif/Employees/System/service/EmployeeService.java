package com.sherif.Employees.System.service;


import com.sherif.Employees.System.model.Employee;

import java.util.List;

public interface EmployeeService {
    public void save(Employee employee);
    List<Employee> getAllEmployees();

}
