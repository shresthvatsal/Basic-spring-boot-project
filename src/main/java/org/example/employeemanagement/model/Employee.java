package org.example.employeemanagement.model;

import org.example.employeemanagement.constants.Constants;
import lombok.Builder;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = Constants.VALIDATION_NAME)
    @Size(min = 2, max = 50, message = Constants.VALIDATION_NAME_SIZE)
    private String name;

    @NotBlank(message = Constants.VALIDATION_EMAIL)
    @Email(message = Constants.VALIDATION_EMAIL_FORMAT)
    private String email;

    @NotBlank(message = Constants.VALIDATION_JOB)
    @Size(min = 2, max = 50, message = Constants.VALIDATION_JOB_SIZE)
    private String job;

    // Default constructor required by JPA
    public Employee() {}

    @Builder
    public Employee(Long id, String name, String email, String job) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getJob() {
        return job;
    }
}