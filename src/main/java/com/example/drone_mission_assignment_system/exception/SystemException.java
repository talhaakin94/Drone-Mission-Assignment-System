package com.example.drone_mission_assignment_system.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SystemException extends RuntimeException{
    private final HttpStatus httpStatus;
    public SystemException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
