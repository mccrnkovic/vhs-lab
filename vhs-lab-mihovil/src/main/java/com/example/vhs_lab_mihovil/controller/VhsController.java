package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.VhsDto;
import com.example.vhs_lab_mihovil.service.VhsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/vhs")
public class VhsController {
    private VhsService vhsService;

    @Autowired
    public VhsController(VhsService vhsService) {
        this.vhsService = vhsService;
    }

    @GetMapping("getAll")
    public List<VhsDto> getAllVhs() {
        List<VhsDto> result = vhsService.getAllVhs();
        return result;
    }
}
