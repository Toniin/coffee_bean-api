package com.coffee_bean.exceptions;

import com.coffee_bean.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ControllerAdvice: Handles exceptions thrown by the controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    // When a ProductNotFoundException is thrown, it will be handled by this method
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIError> productNotFound(ProductNotFoundException exception){
     return new ResponseEntity<>(new APIError(404, exception.getMessage()), HttpStatus.NOT_FOUND);
    };

    // When a CategoryNotFoundException is thrown, it will be handled by this method
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<APIError> productNotFound(CategoryNotFoundException exception){
        return new ResponseEntity<>(new APIError(404, exception.getMessage()), HttpStatus.NOT_FOUND);
    };
}
