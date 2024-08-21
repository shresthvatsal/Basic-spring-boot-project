package employeemanagement;

import static org.junit.jupiter.api.Assertions.*;

import employeemanagement.constants.Constants;
import employeemanagement.model.Employee;
import employeemanagement.repository.EmployeeRepository;
import employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee(1L, "John Doe", "john.doe@example.com", "Developer");
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));

        List<Employee> employees = employeeService.getAllEmployees();

        assertThat(employees).isNotNull();
        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getName()).isEqualTo("John Doe");

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeById_whenEmployeeExists() {
        Employee employee = new Employee(1L, "John Doe", "john.doe@example.com", "Developer");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getName()).isEqualTo("John Doe");

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetEmployeeById_whenEmployeeDoesNotExist() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo(Constants.EMPLOYEE_NOT_FOUND);

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee(null, "Jane Doe", "jane.doe@example.com", "Manager");
        Employee createdEmployee = new Employee(1L, "Jane Doe", "jane.doe@example.com", "Manager");
        when(employeeRepository.save(any(Employee.class))).thenReturn(createdEmployee);

        Employee result = employeeService.createEmployee(employee);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Jane Doe");

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee existingEmployee = new Employee(1L, "John Doe", "john.doe@example.com", "Developer");
        Employee updatedEmployee = new Employee(1L, "John Smith", "john.smith@example.com", "Manager");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("John Smith");
        assertThat(result.getEmail()).isEqualTo("john.smith@example.com");
        assertThat(result.getJob()).isEqualTo("Manager");

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testDeleteEmployee_whenEmployeeExists() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteEmployee_whenEmployeeDoesNotExist() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(1L);
        });

        assertThat(exception.getMessage()).isEqualTo(Constants.EMPLOYEE_NOT_FOUND);

        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(0)).deleteById(1L);
    }
}