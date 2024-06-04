package com.example.demo.DTO;

import com.example.demo.entity.Employee;

public class EmployeeMapper {


    public static EmployeeDTO convertEntityToDto(Employee emp){
        return  EmployeeDTO.builder()
                .id(emp.getId())
                .email(emp.getEmail())
                .nom(emp.getFirstName()).test(emp.getLastName())
                .build();
    }

    public static EmployeeDTO convertDtoToEntity(EmployeeDTO empDto){

        return  EmployeeDTO.builder()
                .id(empDto.getId())
                .email(empDto.getEmail())
                .nom(empDto.getNom())
                .build();
    }
}
