package com.emsbackend.springbootrestapiforemployeemanagementsystemems.service;

import com.emsbackend.springbootrestapiforemployeemanagementsystemems.dto.EmployeeDto;
import com.emsbackend.springbootrestapiforemployeemanagementsystemems.entity.Employee;
import com.emsbackend.springbootrestapiforemployeemanagementsystemems.mapper.EmployeeMapper;
import com.emsbackend.springbootrestapiforemployeemanagementsystemems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    //----------Create Operation----------//
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);     //EmployeeDto to Employee JPA Entity
        Employee savedEmployee = employeeRepository.save(employee);       //Save Employee JPA Entity to Database

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);           //Return saved Employee Object back to the client. So, convert Employee JPA Entity into EmployeeDto
    }

    //----------Get Employee By Id----------//
    public EmployeeDto getEmployeeById(Long employeeId){

        Employee findEmployeeById = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee is not exit with ID: " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(findEmployeeById);
    }

    //----------Get All Employees----------//
    public List<EmployeeDto> getAllEmployees(){

        List<Employee> listOfEmployees = employeeRepository.findAll();

        return listOfEmployees.stream().map((EmployeeMapper::mapToEmployeeDto))
                .collect(Collectors.toList());

    }

    //----------Update Employee----------//
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee is not exists with given ID: " + employeeId));

        employee.setFirstname(updatedEmployeeDto.getFirstName());
        employee.setLastname(updatedEmployeeDto.getLastName());
        employee.setEmail(updatedEmployeeDto.getEmail());

        Employee updateEmployeeInformation = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeInformation);
    }

    //----------Delete Employee----------//
    public void deleteEmployee(Long employeeId) {

        Employee findEmployeeById = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee is not exit with ID: " + employeeId));

        employeeRepository.delete(findEmployeeById);
    }



}
