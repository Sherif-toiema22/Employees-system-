package com.sherif.Employees.System.service;

import com.sherif.Employees.System.model.Employee;
import com.sherif.Employees.System.repositroy.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = null;
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
        }
        else {
            throw new RuntimeException("Employee not found"+id);
        }
        return employee;
    }

    @Override
    public void deleteEmployee(long id) {
        this.employeeRepository.deleteById(id);
    }


    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        return employeeRepository.findAll(pageable);
    }
}
