package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.exception.NoDataFoundException;
import com.example.vhs_lab_mihovil.exception.NotDeletedException;
import com.example.vhs_lab_mihovil.exception.VhsUnavailableException;
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
    public ResponseEntity insertRental(@Valid @RequestBody RentalDto rentalDto) throws NoDataFoundException, VhsUnavailableException {
        RentalDto newRentalDto = rentalService.insertRental(rentalDto);
        return new ResponseEntity(newRentalDto, HttpStatus.OK);
    }

    @PostMapping("return")
    public ResponseEntity rentalReturn(@RequestParam Integer rentalId) throws NoDataFoundException {
        RentalDto rentalDto = rentalService.rentalReturn(rentalId);
        return new ResponseEntity(rentalDto, HttpStatus.OK);
    }

    @PutMapping("updateRental")
    public ResponseEntity updateRental(@Valid @RequestBody RentalDto rentalDto) {
        RentalDto updatedRentalDto = rentalService.updateRental(rentalDto);
        return new ResponseEntity(updatedRentalDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteRental")
    public ResponseEntity deleteRental(@RequestParam Integer rentalId) throws NotDeletedException {
        rentalService.deleteRental(rentalId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
