package com.DevConnect.BE.ExceptionH;

import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler
{
    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<SimpleResponse> AlreadyExistsHandler(AlreadyExistsException e)
    {
        return new ResponseEntity<>(new SimpleResponse(e.message, false), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = AuthenticateFailureException.class)
    public ResponseEntity<SimpleResponse> AuthenticateFailureHandler(AuthenticateFailureException e)
    {
        return new ResponseEntity<>(new SimpleResponse(e.message, false), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<SimpleResponse> ResourceNotFoundHandler(ResourceNotFoundException e)
    {
        return new ResponseEntity<>(new SimpleResponse(e.message, false), HttpStatus.NOT_FOUND);
    }
}
