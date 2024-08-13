package com.demo.curd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({BookAlreadyExistException.class})
    public  ResponseEntity<Object> handleBookAlreadyExistException(BookAlreadyExistException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage()) ;
    }
}
