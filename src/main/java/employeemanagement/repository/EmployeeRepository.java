package employeemanagement.repository;

import employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Employee} entities.
 * It provides CRUD operations as well as additional methods for interacting with the Employee data.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}