package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String nom;
    private String email;
    private String test;
}
