package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.service.RentalService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rental")
public class RentalController {
    private RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("getAll")
    public List<RentalDto> getAllRentals() {
        List<RentalDto> result = rentalService.getAllRentals();
        return result;
    }

    @PostMapping("insertRental")
    public ResponseEntity insertRental(@Valid @RequestBody RentalDto rentalDto) {
        Integer insertedId = rentalService.insertRental(rentalDto);
        return new ResponseEntity(HttpStatus.OK);
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

}
