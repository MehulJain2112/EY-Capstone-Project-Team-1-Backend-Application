package com.ey.ecalendar.controller;

import com.ey.ecalendar.model.Employee;
import com.ey.ecalendar.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")//--> to connect react
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
    	List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}