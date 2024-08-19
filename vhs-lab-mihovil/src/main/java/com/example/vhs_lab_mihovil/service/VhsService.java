package com.example.vhs_lab_mihovil.service;

import com.example.vhs_lab_mihovil.dto.VhsDto;
import com.example.vhs_lab_mihovil.exception.NoDataFoundException;
import com.example.vhs_lab_mihovil.exception.NotDeletedException;
import com.example.vhs_lab_mihovil.mapper.VhsMapper;
import com.example.vhs_lab_mihovil.model.Vhs;
import com.example.vhs_lab_mihovil.repository.VhsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Transactional
    public VhsDto insertVhs(VhsDto vhsDto) {
        Vhs vhs = VhsMapper.MAPPER.toModel(vhsDto);
        vhs = vhsRepository.save(vhs);

        VhsDto newVhsDto = VhsMapper.MAPPER.toDto(vhs);
        return newVhsDto;
    }

    @Transactional
    public VhsDto updateVhs(VhsDto vhsDto) {
        if (vhsDto.getId() == null) {
            throw new IllegalStateException("update.id.null");
        } else {
            Vhs vhs = VhsMapper.MAPPER.toModel(vhsDto);
            vhs = vhsRepository.save(vhs);

            VhsDto newVhsDto = VhsMapper.MAPPER.toDto(vhs);
            return newVhsDto;
        }

    }

    @Transactional
    public boolean deleteVhs(Integer vhsId) throws NotDeletedException {
        if (vhsId == null) {
            throw new IllegalStateException("update.id.null");
        }
        int deleted = vhsRepository.deleteVhsByid(vhsId);
        if (deleted == 0) {
            throw new NotDeletedException(vhsRepository, vhsId.toString());
        }
        return true;
    }

    public Vhs getVhsById(Integer id) throws NoDataFoundException {
        Optional<Vhs> optionalVhs = vhsRepository.findById(id);
        if (optionalVhs.isPresent()) {
            return optionalVhs.get();
        } else {
            throw new NoDataFoundException(vhsRepository, id.toString());
        }
    }

    @Transactional
    public int updateAvailability(Integer vhsId, Boolean available) {
        int updated = vhsRepository.updateAvailability(vhsId, available);
        return updated;
    }
}
