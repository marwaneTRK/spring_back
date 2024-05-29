package com.example.demo.api;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/emp")
public class EmployeeController {
    @Autowired
    private EmployeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.findAllEmployees();
    }




    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }
    @DeleteMapping("/employees/{id}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/employees/{id}/update")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId,employee);
    }
}
