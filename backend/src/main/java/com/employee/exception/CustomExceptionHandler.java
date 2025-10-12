package com.employee.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler extends Exception{
    public CustomExceptionHandler(String message) {
        super(message);
    }
    @ExceptionHandler(CustomExceptionHandler.class)
    public String handleCustomException(CustomExceptionHandler ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex) {
        return "An error occurred: " + ex.getMessage();
    }
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex) {

        // return "A runtime error occurred: " + ex.getMessage();
    return new ResponseEntity<>("A runtime error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR).toString();

    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("A null pointer error occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String>entityNotFoundExceptionHander(EntityNotFoundException ex){
        return new ResponseEntity<>("Entity Does not Exist" + ex.getMessage(),HttpStatus.NOT_FOUND);
    }





}
