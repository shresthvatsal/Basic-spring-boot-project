package employeemanagement.service;

import employeemanagement.constants.Constants;
import employeemanagement.model.Employee;
import employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles business logic for Employee entities.
 */
@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    /**
     * Constructs an EmployeeService with the required EmployeeRepository.
     *
     * @param employeeRepository The repository for employee operations.
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return A list of {@link Employee} objects.
     */
    public List<Employee> getAllEmployees() {
        logger.debug("Fetching all employees");
        return employeeRepository.findAll();
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The {@link Employee} object.
     * @throws RuntimeException if the employee is not found.
     */
    public Employee getEmployeeById(Long id) {
        logger.debug("Fetching employee with ID: {}", id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElseThrow(() -> new RuntimeException(Constants.EMPLOYEE_NOT_FOUND));
    }

    /**
     * Creates a new employee.
     *
     * @param employee The {@link Employee} object to create.
     * @return The created {@link Employee} object.
     */
    public Employee createEmployee(Employee employee) {
        logger.debug("Creating employee: {}", employee.getName());
        return employeeRepository.save(employee);
    }

    /**
     * Updates an existing employee.
     *
     * @param id             The ID of the employee to update.
     * @param updatedEmployee The updated {@link Employee} object.
     * @return The updated {@link Employee} object.
     * @throws RuntimeException if the employee to update is not found.
     */
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

    /**
     * Deletes an employee by their ID.
     *
     * @param id The ID of the employee to delete.
     * @throws RuntimeException if the employee to delete is not found.
     */
    public void deleteEmployee(Long id) {
        logger.debug("Deleting employee with ID: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException(Constants.EMPLOYEE_NOT_FOUND);
        }
    }
}