package com.course.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleExceptions( StudentNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(StudentBadRequestException.class)
    public ResponseEntity<Object> handleExceptions( StudentBadRequestException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Bad Request");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }
    @ExceptionHandler(StudentUnAuthorizedException.class)
    public ResponseEntity<Object> handleExceptions( StudentUnAuthorizedException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Unauthorized");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
        return entity;
    }

    @ExceptionHandler(StudentInternalServerException.class)
    public ResponseEntity<Object> handleExceptions( StudentInternalServerException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Internal Server Error");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
    @ExceptionHandler(StudentForbiddenException.class)
    public ResponseEntity<Object> handleExceptions( StudentForbiddenException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("You do not have the right credential to access resourec");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        return entity;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleExceptions( UserNotFoundException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("User not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(UserBadRequestException.class)
    public ResponseEntity<Object> handleExceptions( UserBadRequestException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Bad Request");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }

}