package org.example.employeemanagement.service;

import org.example.employeemanagement.constants.Constants;
import org.example.employeemanagement.model.Employee;
import org.example.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        logger.debug("Fetching all employees");
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        logger.debug("Fetching employee with ID: {}", id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElseThrow(() -> new RuntimeException(Constants.EMPLOYEE_NOT_FOUND));
    }

    public Employee createEmployee(Employee employee) {
        logger.debug("Creating employee: {}", employee.getName());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        logger.debug("Updating employee with ID: {}", id);
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee = Employee.builder()
                .id(existingEmployee.getId())
                .name(updatedEmployee.getName())
                .email(updatedEmployee.getEmail())
                .job(updatedEmployee.getJob())
                .build();
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        logger.debug("Deleting employee with ID: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException(Constants.EMPLOYEE_NOT_FOUND);
        }
    }
}
