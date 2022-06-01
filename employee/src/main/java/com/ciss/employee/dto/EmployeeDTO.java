package com.ciss.employee.dto;

import com.ciss.employee.model.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EmployeeDTO {

    private Long id;

    @NotNull(message = "employees-6")
    @Size(min = 2, max = 30, message = "employees-1")
    @JsonProperty(value = "first_name", required = true)
    private String firstName;

    @NotNull(message = "employees-7")
    @Size(min = 2, max = 50, message = "employees-2")
    @JsonProperty(value = "last_name", required = true)
    private String lastName;

    @NotBlank(message = "employees-3")
    @Email(message = "employees-4")
    @JsonProperty(value = "email", required = true)
    private String email;

    @NotNull(message = "employees-5")
    @JsonProperty(value = "nis_pis", required = true)
    private Integer nisPis;

    public Employee toModel() {
        Employee employee = new Employee();

        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setEmail(this.email);
        employee.setNisPis(Integer.toString(this.nisPis));

        return employee;
    }
}