package com.ciss.employee.service;

import com.ciss.employee.dto.EmployeeDTO;
import com.ciss.employee.exception.EmployeeNotFoundException;
import com.ciss.employee.model.Employee;
import com.ciss.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepositoryMocked;

    @InjectMocks
    private EmployeeService employeeService;

    List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        Employee employee = new Employee();
        employee.setFirstName("Mateus");
        employee.setLastName("Peixoto");
        employee.setEmail("mateustomoo@gmail.com");
        employee.setNisPis("123456");
        employee.setId(10L);

        employeeList.add(employee);
    }

    @Test
    public void should_create_new_employee() {
        Employee employee = new Employee();
        employee.setFirstName("Mateus");
        employee.setLastName("Peixoto");
        employee.setEmail("mateustomoo@gmail.com");
        employee.setNisPis("123456");
        employee.setId(10L);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setNisPis(Integer.parseInt(employee.getNisPis()));
        employeeDTO.setId(employee.getId());

        when(employeeRepositoryMocked.save(any(Employee.class))).thenReturn(employee);
        Employee employeeSaved = employeeService.saveEmployee(employeeDTO);

        assertEquals(employeeSaved.getId(), 10L);
        assertEquals(employeeSaved.getFirstName(), "Mateus");
        assertEquals(employeeSaved.getLastName(), "Peixoto");
        assertEquals(employeeSaved.getEmail(), "mateustomoo@gmail.com");
        assertEquals(employeeSaved.getNisPis(), "123456");
    }

    @Test
    public void should_delete_a_employee() {
        Employee employee = new Employee();
        employee.setFirstName("Mateus");
        employee.setLastName("Peixoto");
        employee.setEmail("mateustomoo@gmail.com");
        employee.setNisPis("123456");
        employee.setId(10L);

        when(employeeRepositoryMocked.findById(10L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(10L);

        when(employeeRepositoryMocked.findById(10L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
           employeeService.findEmployee(10L);
        });
    }

    @Test
    public void should_fail_delete_because_dont_exist_employee() {
        when(employeeRepositoryMocked.findById(10L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
           employeeService.deleteEmployee(10L);
        });
    }

    @Test
    public void should_update_a_employee() {
        Employee employee = new Employee();
        employee.setFirstName("Mateus");
        employee.setLastName("Peixoto");
        employee.setEmail("mateustomoo@gmail.com");
        employee.setNisPis("12345678");
        employee.setId(10L);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setNisPis(Integer.parseInt(employee.getNisPis()));
        employeeDTO.setId(employee.getId());

        when(employeeRepositoryMocked.findById(10L)).thenReturn(Optional.of(this.employeeList.get(0)));
        when(employeeRepositoryMocked.save(any(Employee.class))).thenReturn(employee);

        assertEquals(employeeService.updateEmployee(10L, employeeDTO), employee);
    }
}
