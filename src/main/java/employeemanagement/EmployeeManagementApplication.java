package employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Employee Management application.
 * This class bootstraps the Spring Application context.
 */
@SpringBootApplication
public class EmployeeManagementApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }
}