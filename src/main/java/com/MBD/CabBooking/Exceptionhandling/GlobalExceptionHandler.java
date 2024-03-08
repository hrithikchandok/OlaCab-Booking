package com.MBD.CabBooking.Exceptionhandling;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.jpa.repository.query.EqlParser.New_valueContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.MBD.CabBooking.utility.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundExcpetion.class)
    public ResponseEntity<ApiResponse> resourcenotfound(ResourceNotFoundExcpetion ex) {
        String message = ex.getMessage();
        
        return new ResponseEntity<>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(OneToOneViolationException.class)
    public ResponseEntity<ApiResponse> handleOneToOneViolationException(OneToOneViolationException ex) {


        return new ResponseEntity<>( new ApiResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = "Unique constraint violation occurred. Please check your input.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<ApiResponse> onetooneExecption(CustomException ex) {
//        String message = ex.getMessage();
//        
//        return new ResponseEntity<>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
//    }
    
//    @ExceptionHandler(value= {java.sql.SQLIntegrityConstraintViolationException.class})
//    public ResponseEntity<ApiResponse> CustomException(java.sql.SQLIntegrityConstraintViolationException ex) {
//        
//        
//        return new ResponseEntity<>(new ApiResponse("SQl Voilation"+ex.getMessage(), false), HttpStatus.NOT_FOUND);
//    }
//    
//    @ExceptionHandler(value= {org.springframework.web.HttpMediaTypeNotAcceptableException.class})
//    public ResponseEntity<ApiResponse> CustomException1(org.springframework.web.HttpMediaTypeNotAcceptableException ex) {
//        
//        
//        return new ResponseEntity<>(new ApiResponse("Http media"+ex.getMessage(), false), HttpStatus.NOT_FOUND);
//    }
    
}