package com.product.management.product_management.exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@ControllerAdvice
public class CustomException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> setExceptionDetails(ResourceNotFoundException resourceNotFoundException){

        return new ResponseEntity<> (ErrorDTO.builder ()
                .statusCode ( HttpStatus.NOT_FOUND.value () )
                //.path ( request.getURI ().toString () )
                .timeStamp ( Date.from ( Instant.now () ) )
                .errorMessage ( resourceNotFoundException.getMessage () )
                .build (), HttpStatus.NOT_FOUND);
    }
}
