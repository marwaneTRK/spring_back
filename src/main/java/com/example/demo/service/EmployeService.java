package com.example.demo.service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.EmployeeMapper;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }
    public ResponseEntity<?> findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<String> deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
            return ResponseEntity.ok("Deleted Succefully");
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<String> addEmployee(Employee employee) {
        Optional<Employee> user = employeeRepository.findByEmail(employee.getEmail());
        if (user.isPresent()) {
            return new ResponseEntity<>("Employee with this email already exists.", HttpStatus.CONFLICT);
        }else {
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee created successfully.", HttpStatus.CREATED);
        }
    }
    public ResponseEntity<Employee> updateEmployee(Long employeeId,Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employeeUpdated = optionalEmployee.get();
            employeeUpdated.setFirstName(employee.getFirstName());
            employeeUpdated.setLastName(employee.getLastName());
            employeeUpdated.setEmail(employee.getEmail());
            employeeRepository.save(employeeUpdated);
            return ResponseEntity.ok(employeeUpdated);
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<?> getEmplpyeeWorkHours(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            return ResponseEntity.ok(employeeRepository.sumWorkHoursByEmployeeId(employeeOptional.get().getId()));
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<?> getAbsentEmployees (){
        List<Employee> employees = employeeRepository.findEmployeesWithZeroWorkHours();
        if(employees.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }
}
