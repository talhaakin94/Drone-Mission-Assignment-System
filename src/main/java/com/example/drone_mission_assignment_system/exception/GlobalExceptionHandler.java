package com.example.drone_mission_assignment_system.exception;
import com.example.drone_mission_assignment_system.dto.SystemErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<SystemErrorResponse> handleException(Exception exception) {
        SystemErrorResponse response = new SystemErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<SystemErrorResponse> handleSystemException(SystemException systemException) {
        SystemErrorResponse response = new SystemErrorResponse(systemException.getMessage(), systemException.getHttpStatus().value());
        return new ResponseEntity<>(response, systemException.getHttpStatus());
    }
}
