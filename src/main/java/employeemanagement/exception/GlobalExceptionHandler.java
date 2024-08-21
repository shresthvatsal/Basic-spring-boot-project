package employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler handles exceptions globally across the entire application
 * and provides meaningful responses to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles RuntimeException and sends a 404 Not Found response.
     *
     * @param ex The RuntimeException to be handled.
     * @return A ResponseEntity containing the exception message and a 404 Not Found status.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException and sends a 400 Bad Request response.
     * It creates a map containing validation field names and corresponding error messages.
     *
     * @param ex The MethodArgumentNotValidException to be handled.
     * @return A ResponseEntity containing a map of field names and error messages, and a 400 Bad Request status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}