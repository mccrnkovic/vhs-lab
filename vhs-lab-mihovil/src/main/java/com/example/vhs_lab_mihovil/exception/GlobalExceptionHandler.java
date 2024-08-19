package com.example.vhs_lab_mihovil.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler
    public ResponseEntity handle(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        StringBuilder validationMessage = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            validationMessage.append(fieldError.getField()).append(" ")
                    .append(fieldError.getDefaultMessage()).append("\n");
        }
        return new ResponseEntity(validationMessage.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ResponseEntity handle(NotDeletedException e) {
        String message;
        try {
            message = messageSource.getMessage(e.getMessage(), new Object[]{e.getRepositoryName(), e.getId()}, new Locale(""));
        } catch (NoSuchMessageException noSuchMessageException) {
            message = e.getMessage();
        }

        log.warn(message);
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ResponseEntity handle(NoDataFoundException e) {
        String message;
        try {
            message = messageSource.getMessage(e.getMessage(), new Object[]{e.getRepositoryName(), e.getId()}, new Locale(""));
        } catch (NoSuchMessageException noSuchMessageException) {
            message = e.getMessage();
        }

        log.warn(message);
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ResponseEntity handle(VhsUnavailableException e) {
        String message;
        try {
            message = messageSource.getMessage(e.getMessage(), new Object[]{e.getVhsId()}, new Locale(""));
        } catch (NoSuchMessageException noSuchMessageException) {
            message = e.getMessage();
        }

        log.warn(message);
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handle(Exception e) {
        String message = null;
        try {
            message = messageSource.getMessage(e.getMessage(), new Object[]{}, new Locale("en"));
        } catch (NoSuchMessageException noSuchMessageException) {
            message = e.getMessage();
        }

        log.warn(message);
        e.printStackTrace();
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
}
