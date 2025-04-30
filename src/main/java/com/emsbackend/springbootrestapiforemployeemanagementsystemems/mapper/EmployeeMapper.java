package com.emsbackend.springbootrestapiforemployeemanagementsystemems.mapper;

import com.emsbackend.springbootrestapiforemployeemanagementsystemems.dto.EmployeeDto;
import com.emsbackend.springbootrestapiforemployeemanagementsystemems.entity.Employee;

public class EmployeeMapper {

    //Employee to EmployeeDto
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail()
        );
    }

    //EmployeeDto to Employee
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
