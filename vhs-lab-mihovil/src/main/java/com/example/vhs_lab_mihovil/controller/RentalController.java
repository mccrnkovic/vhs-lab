package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.service.RentalService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/api/rental")
public class RentalController {
    private RentalService rentalService;
    private MessageSource messageSource;

    @Autowired
    public RentalController(RentalService rentalService, MessageSource messageSource) {
        this.rentalService = rentalService;
        this.messageSource = messageSource;
    }

    @GetMapping("getAll")
    public ResponseEntity getAllRentals() {
        List<RentalDto> result = rentalService.getAllRentals();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("insertRental")
    public ResponseEntity insertRental(@Valid @RequestBody RentalDto rentalDto) {
        Integer insertedId = rentalService.insertRental(rentalDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("updateRental")
    public ResponseEntity updateRental(@Valid @RequestBody RentalDto rentalDto) {
        Integer updatedId = rentalService.updateRental(rentalDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteRental")
    public ResponseEntity deleteRental(@RequestParam Integer rentalId) {
        rentalService.deleteRental(rentalId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler
    public ResponseEntity handle(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        StringBuilder validationMessage = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            validationMessage.append(fieldError.getDefaultMessage()).append("\n");
        }
        return new ResponseEntity(validationMessage.toString(), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

}
