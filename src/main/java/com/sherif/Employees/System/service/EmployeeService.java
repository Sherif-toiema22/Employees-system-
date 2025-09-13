package com.sherif.Employees.System.service;


import com.sherif.Employees.System.model.Employee;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    void deleteEmployee(long id);

}
