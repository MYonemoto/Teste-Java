package com.ciss.employee.service;

import com.ciss.employee.dto.EmployeeDTO;
import com.ciss.employee.exception.EmployeeNotFoundException;
import com.ciss.employee.model.Employee;
import com.ciss.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.save(employeeDTO.toModel());
    }

    public Employee findEmployee(Long id) {
        Optional<Employee> employeeById = employeeRepository.findById(id);

        if (!employeeById.isPresent())
            throw new EmployeeNotFoundException();

        return employeeById.get();
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeById = employeeRepository.findById(id);

        if (!employeeById.isPresent())
            throw new EmployeeNotFoundException();

        Employee employee = employeeById.get();
        employee.setId(id);
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setNisPis(employeeDTO.getNisPis().toString());
        employee.setUpdatedAt(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> employeeById = employeeRepository.findById(id);

        if (!employeeById.isPresent())
            throw new EmployeeNotFoundException();

        employeeRepository.deleteById(id);
    }
}
