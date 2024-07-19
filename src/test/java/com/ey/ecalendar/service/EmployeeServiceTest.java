package com.ey.ecalendar.service;

import com.ey.ecalendar.model.Employee;
import com.ey.ecalendar.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceTest.class);

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        logger.info("Starting test for getAllEmployees");

        // Mocking data
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("Mehul");
        emp1.setPosition("Developer");
        emp1.setDepartment("IT");

        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("John");
        emp2.setPosition("Manager");
        emp2.setDepartment("Sales");

        List<Employee> employeeList = Arrays.asList(emp1, emp2);
        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Test case: Pass - Check size of list
        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();

        logger.info("Completed test for getAllEmployees");
    }

    @Test
    public void testGetEmployeeById() {
        logger.info("Starting test for getEmployeeById");

        Long employeeId = 1L;
        Employee emp = new Employee();
        emp.setId(employeeId);
        emp.setName("Mehul");
        emp.setPosition("Developer");
        emp.setDepartment("IT");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(employeeId);

        assertEquals(employeeId, result.getId());
        assertEquals("Mehul", result.getName());
        assertEquals("Developer", result.getPosition());
        assertEquals("IT", result.getDepartment());

        verify(employeeRepository, times(1)).findById(employeeId);

        logger.info("Completed test for getEmployeeById");
    }

    @Test
    public void testSaveEmployee() {
        logger.info("Starting test for saveEmployee");

        Employee empToSave = new Employee();
        empToSave.setName("Alice");
        empToSave.setPosition("Engineer");
        empToSave.setDepartment("Engineering");

        when(employeeRepository.save(empToSave)).thenReturn(empToSave);

        Employee savedEmployee = employeeService.saveEmployee(empToSave);

        assertEquals("Alice", savedEmployee.getName());
        assertEquals("Engineer", savedEmployee.getPosition());
        assertEquals("Engineering", savedEmployee.getDepartment());

        verify(employeeRepository, times(1)).save(empToSave);

        logger.info("Completed test for saveEmployee");
    }

    @Test
    public void testUpdateEmployee() {
        logger.info("Starting test for updateEmployee");

        Long employeeId = 1L;
        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setName("John");
        existingEmployee.setPosition("Manager");
        existingEmployee.setDepartment("Sales");

        Employee updatedEmployeeData = new Employee();
        updatedEmployeeData.setName("John Doe");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Employee updatedEmployee = employeeService.updateEmployee(employeeId, updatedEmployeeData);

        assertEquals("John Doe", updatedEmployee.getName());
        assertEquals("Manager", updatedEmployee.getPosition()); // Position should not change
        assertEquals("Sales", updatedEmployee.getDepartment()); // Department should not change

        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).save(existingEmployee);

        logger.info("Completed test for updateEmployee");
    }

    @Test
    public void testDeleteEmployee() {
        logger.info("Starting test for deleteEmployee");

        Long employeeId = 1L;
        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setName("Jane");
        existingEmployee.setPosition("HR");
        existingEmployee.setDepartment("Human Resources");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);

        logger.info("Completed test for deleteEmployee");
    }
}