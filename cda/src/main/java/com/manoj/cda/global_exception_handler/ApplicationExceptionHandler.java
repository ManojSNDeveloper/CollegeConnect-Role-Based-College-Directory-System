package com.manoj.cda.global_exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.manoj.cda.response_structure.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler 
{

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseStructure<String>> handleRuntime(RuntimeException ex) {
        ResponseStructure<String> rs = ResponseStructure.<String>builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message("Login failed")
            .body(ex.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
    }
}

