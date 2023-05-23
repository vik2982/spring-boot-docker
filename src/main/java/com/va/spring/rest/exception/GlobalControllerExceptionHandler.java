package com.va.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(FootballTeamException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

    ErrorResponse error = new ErrorResponse();
    error.setMessage(ex.getMessage());
    return new ResponseEntity<ErrorResponse>(error, HttpStatus.SERVICE_UNAVAILABLE);

  }

  @ExceptionHandler(FootballTeamNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleTeamNotFound(Exception ex) {

    ErrorResponse error = new ErrorResponse();
    error.setMessage(ex.getMessage());
    return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);

  }
}
