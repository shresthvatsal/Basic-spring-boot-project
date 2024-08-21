package employeemanagement.controller;

import employeemanagement.constants.Constants;
import employeemanagement.model.Employee;
import employeemanagement.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller for managing CRUD operations for Employee entities.
 */
@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Constructs an EmployeeController with the needed EmployeeService.
     *
     * @param employeeService The service responsible for employee operations.
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return A ResponseEntity containing a list of {@link Employee} objects.
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return A ResponseEntity containing the {@link Employee} object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    /**
     * Creates a new employee.
     *
     * @param employee The employee object to create.
     * @return A ResponseEntity containing the created {@link Employee} object.
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(createdEmployee);
    }

    /**
     * Updates an existing employee.
     *
     * @param id The ID of the employee to update.
     * @param employee The updated employee object.
     * @return A ResponseEntity containing the updated {@link Employee} object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Deletes an employee by their ID.
     *
     * @param id The ID of the employee to delete.
     * @return A ResponseEntity containing a success message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(Constants.EMPLOYEE_DELETED_SUCCESS);
    }
}