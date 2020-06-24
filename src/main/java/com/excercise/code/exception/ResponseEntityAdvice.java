package com.excercise.code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseEntityAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {CTSBusinessException.class})
  public ResponseEntity businessException(CTSBusinessException exec) {
    return new ResponseEntity(
            exec.getErrorMessage(), HttpStatus.valueOf(exec.getErrorCode()));
  }
}
