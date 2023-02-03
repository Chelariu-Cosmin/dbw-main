package com.software.application.data.service;

import com.software.application.data.entity.Employee;
import com.software.application.data.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> get(Long id) {
        return employeeRepository.findById(id);
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            System.err.println("Employee is null. Are you sure you have connected your form to the application?");
            return;
        }
         employeeRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Page<Employee> list(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> list(Pageable pageable, Specification<Employee> filter) {
        return employeeRepository.findAll(filter, pageable);
    }

    public int count() {
        return (int) employeeRepository.count();
    }

    public List<Employee> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return employeeRepository.findAll();
        } else {
            return employeeRepository.search(stringFilter);
        }
    }
}
