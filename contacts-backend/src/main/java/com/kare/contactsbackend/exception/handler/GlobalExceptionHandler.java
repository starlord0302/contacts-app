package com.kare.contactsbackend.exception.handler;

import com.kare.contactsbackend.exception.custom.ContactNotFoundException;
import com.kare.contactsbackend.exception.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ContactNotFoundException.class)
  public ResponseEntity<ApiError> handleContactNotFoundException(ContactNotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

}
