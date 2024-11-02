package com.home.trupper.rest.controller;

import com.home.trupper.rest.exception.dto.ErrorDTO;
import com.home.trupper.rest.exception.TrupperPracticeException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<ErrorDTO> servletRequestBindingHandler(ServletRequestBindingException ex){
        ErrorDTO  errorDTO= ErrorDTO.builder()
                .code("ERR-003")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TrupperPracticeException.class)
    public ResponseEntity<ErrorDTO> PracticeExceptionHandler(TrupperPracticeException ex){
        ErrorDTO  errorDTO= ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorDTO> jwtExceptionHandler(JwtException ex){
        ErrorDTO  errorDTO= ErrorDTO.builder()
                .code("ERR-002")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
