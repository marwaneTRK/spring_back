package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    @Query("SELECT SUM(p.workHours) FROM Presence p WHERE p.employee.id = :employeeId")
    Double sumWorkHoursByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT e FROM Employee e WHERE NOT EXISTS (SELECT 1 FROM Presence p WHERE p.employee = e) OR " +
            "(SELECT SUM(p.workHours) FROM Presence p WHERE p.employee = e) = 0")
    List<Employee> findEmployeesWithZeroWorkHours();
}
