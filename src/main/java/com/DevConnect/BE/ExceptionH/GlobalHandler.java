package com.DevConnect.BE.ExceptionH;

import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalHandler
{
    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<SimpleResponse> AlreadyExistsHandler(AlreadyExistsException e)
    { return new ResponseEntity<>(new SimpleResponse(e.message, false), HttpStatus.CONFLICT); }

    @ExceptionHandler(value = AuthenticateFailureException.class)
    public ResponseEntity<SimpleResponse> AuthenticateFailureHandler(AuthenticateFailureException e)
    { return new ResponseEntity<>(new SimpleResponse(e.message, false), HttpStatus.FORBIDDEN); }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<SimpleResponse> ResourceNotFoundHandler(ResourceNotFoundException e)
    { return new ResponseEntity<>(new SimpleResponse(e.message, false), HttpStatus.NOT_FOUND); }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<SimpleResponse> RuntimeExceptionHandler(RuntimeException r) //GENERIC EXCEPTIONHANDLER SHOULD CATCH ALL OTHER KINDS OF RUNTIME EXCEPTION
    { return new ResponseEntity<>(new SimpleResponse(r.getMessage(), false), HttpStatus.BAD_REQUEST); }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<SimpleResponse> NoResourceFoundExceptionHandler(NoResourceFoundException r)
    { return new ResponseEntity<>(new SimpleResponse(r.getMessage(), false), HttpStatus.BAD_REQUEST); }
}
