package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.VhsDto;
import com.example.vhs_lab_mihovil.exception.NotDeletedException;
import com.example.vhs_lab_mihovil.service.VhsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/vhs")
public class VhsController {
    private VhsService vhsService;

    @Autowired
    public VhsController(VhsService vhsService) {
        this.vhsService = vhsService;
    }

    @GetMapping("getAllVhs")
    public ResponseEntity getAllVhs() {
        List<VhsDto> result = vhsService.getAllVhs();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("insertVhs")
    public ResponseEntity insertVhs(@RequestBody VhsDto vhsDto) {
        VhsDto newVhsDto = vhsService.insertVhs(vhsDto);
        return new ResponseEntity<>(newVhsDto, HttpStatus.OK);
    }

    @PutMapping("updateVhs")
    public ResponseEntity updateVhs(@RequestBody VhsDto vhsDto) {
        VhsDto newVhsDto = vhsService.updateVhs(vhsDto);
        return new ResponseEntity(newVhsDto, HttpStatus.OK);
    }

    @DeleteMapping("deleteVhs")
    public ResponseEntity deleteVhs(@RequestParam Integer vhsId) throws NotDeletedException {
        boolean deleted = vhsService.deleteVhs(vhsId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
