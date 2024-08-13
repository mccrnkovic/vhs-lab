package com.example.vhs_lab_mihovil.service;

import com.example.vhs_lab_mihovil.dto.VhsDto;
import com.example.vhs_lab_mihovil.mapper.VhsMapper;
import com.example.vhs_lab_mihovil.model.Vhs;
import com.example.vhs_lab_mihovil.repository.VhsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VhsService {
    private VhsRepository vhsRepository;

    @Autowired
    public VhsService(VhsRepository vhsRepository) {
        this.vhsRepository = vhsRepository;
    }

    public List<VhsDto> getAllVhs(){
        List<Vhs> vhsList = vhsRepository.findAll();
        List<VhsDto> vhsDtoList = vhsList.stream()
                .map(VhsMapper.MAPPER::toDto)
                .collect(Collectors.toList());
        return vhsDtoList;
    }
}
