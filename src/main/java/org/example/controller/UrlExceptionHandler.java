package org.example.controller;

import org.example.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URL;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UrlExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(logoutException.class)
    protected ResponseEntity<Object> handleLogout(logoutException ex){
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<Object>(error, error.getStatus());
    }
    @ExceptionHandler(URLisNotFind.class)
    protected ResponseEntity<Object> handleURLisNotFind(URLisNotFind ex){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<Object>(error, error.getStatus());
    }
    @ExceptionHandler(UserExistException.class)
    protected ResponseEntity<Object> handleUserExistException(UserExistException ex){
        ApiError error = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<Object>(error, error.getStatus());
    }
    @ExceptionHandler(UserPasswordIncorrect.class)
    protected ResponseEntity<Object> handleUserPasswordIncorrect(UserPasswordIncorrect ex){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<Object>(error, error.getStatus());
    }
}
