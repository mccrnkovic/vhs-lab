package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.PriceDto;
import com.example.vhs_lab_mihovil.model.PriceType;
import com.example.vhs_lab_mihovil.service.PriceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price")
public class PriceController {

    private PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("getAll")
    public ResponseEntity getAllPrices(){
        List<PriceDto> result = priceService.getAllPrices();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity updatePrice(@Valid @RequestBody PriceDto priceDto){
        PriceDto newPriceDto = priceService.updatePrice(priceDto);
        return new ResponseEntity(newPriceDto, HttpStatus.ACCEPTED);
    }

}
