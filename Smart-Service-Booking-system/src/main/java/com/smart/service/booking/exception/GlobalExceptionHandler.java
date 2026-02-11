package com.smart.service.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler — ensures no internal stack traces or sensitive
 * details are ever returned to clients in HTTP error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Handle @Valid validation failures */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /** Handle business-logic RuntimeExceptions (e.g. "User not found") */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> body = new HashMap<>();
        String message = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred";

        // Return 404 for "not found" scenarios, 400 for other business errors
        if (message.toLowerCase().contains("not found")) {
            body.put("error", "Not Found");
            body.put("message", message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }

        body.put("error", "Request failed");
        body.put("message", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /** Handle Spring Security access denied */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Forbidden");
        body.put("message", "You do not have permission to access this resource");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    /** Handle bad credentials on login */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentials(BadCredentialsException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Unauthorized");
        body.put("message", "Invalid email or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    /** Catch-all for any unhandled exceptions — never leak stack traces */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Internal Server Error");
        body.put("message", "An unexpected error occurred. Please try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
