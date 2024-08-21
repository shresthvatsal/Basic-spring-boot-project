package employeemanagement.model;

import employeemanagement.constants.Constants;
import lombok.Builder;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Entity representing an Employee.
 */
@Entity
@Table(name = "employees")
public class Employee {

    /**
     * The unique identifier for the employee.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the employee.
     * Cannot be blank and must be between 2 and 50 characters.
     */
    @NotBlank(message = Constants.VALIDATION_NAME)
    @Size(min = 2, max = 50, message = Constants.VALIDATION_NAME_SIZE)
    private String name;

    /**
     * The email of the employee.
     * Cannot be blank and must be in a valid email format.
     */
    @NotBlank(message = Constants.VALIDATION_EMAIL)
    @Email(message = Constants.VALIDATION_EMAIL_FORMAT)
    private String email;

    /**
     * The job/title of the employee.
     * Cannot be blank and must be between 2 and 50 characters.
     */
    @NotBlank(message = Constants.VALIDATION_JOB)
    @Size(min = 2, max = 50, message = Constants.VALIDATION_JOB_SIZE)
    private String job;

    /**
     * Default constructor required by JPA.
     */
    public Employee() {}

    /**
     * Constructs an Employee with the specified details.
     *
     * @param id    The unique identifier for the employee.
     * @param name  The name of the employee.
     * @param email The email of the employee.
     * @param job   The job/title of the employee.
     */
    @Builder
    public Employee(Long id, String name, String email, String job) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.job = job;
    }

    /**
     * Returns the unique identifier for the employee.
     *
     * @return The unique identifier for the employee.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the name of the employee.
     *
     * @return The name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email of the employee.
     *
     * @return The email of the employee.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the job/title of the employee.
     *
     * @return The job/title of the employee.
     */
    public String getJob() {
        return job;
    }
}