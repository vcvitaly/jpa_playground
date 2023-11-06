package io.github.vcvitaly.jpaplayground.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * MyExceptionHandler.
 *
 * @author Vitalii Chura
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
