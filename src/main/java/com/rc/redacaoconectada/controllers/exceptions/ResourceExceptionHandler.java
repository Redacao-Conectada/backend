package com.rc.redacaoconectada.controllers.exceptions;

import com.rc.redacaoconectada.services.exceptions.DatabaseException;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError err = new ValidationError();
    err.setTimeStamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Validation exception");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    for(FieldError f : e.getBindingResult().getFieldErrors())
      err.addError(f.getField(), f.getDefaultMessage());

    return ResponseEntity.status(status).body(err);
  }

  private StandardError createStandardError(HttpStatus status, String error, String message, String requestUri){
    return  new StandardError(Instant.now(), status.value(), error, message, requestUri);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = createStandardError(status, e.getMessage(), "Resourse not found", request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> integrityViolation(DatabaseException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = createStandardError(status, e.getMessage(), "Resourse not found", request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

}
