package com.software.application.data.service.summary;

import com.software.application.data.entity.dto.EmployeeDTO;

import java.util.List;

public interface IEmployee {

    EmployeeDTO getEmployeeById(Long id);

    void addEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(EmployeeDTO employeeDTO);

    int count();

    List<EmployeeDTO> findAll(String stringFilter);
}
