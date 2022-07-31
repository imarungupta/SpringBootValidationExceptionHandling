package com.javatechie.validation.advice;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

// Step-1
@RestControllerAdvice
public class ApplicationExceptionHandler {
// Step-2
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){

        Map<String, String> errorMap = new HashMap<>();
        // First get all the bind error message list from MethodArgumentNotValidException
        // Iterate this list and based on its field get the default message
        ex.getBindingResult().getFieldErrors().forEach(error -> { // Iterating the list
                errorMap.put(error.getField(),error.getDefaultMessage()); // putting the error message in map
        });
        return errorMap;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String>handleBusinessException(UserNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;

    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({InvalidDataAccessApiUsageException.class, MethodArgumentTypeMismatchException.class})
    public Map<String, String > handleIllegalArgumentException(InvalidDataAccessApiUsageException ex ){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "Invalid argument!");
        return errorMap;
    }

}
