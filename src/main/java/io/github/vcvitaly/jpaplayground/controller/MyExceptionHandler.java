package io.github.vcvitaly.jpaplayground.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * MyExceptionHandler.
 *
 * @author Vitalii Chura
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(EntityNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }
}
