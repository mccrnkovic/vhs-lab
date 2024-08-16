package com.example.vhs_lab_mihovil.service;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.mapper.RentalMapper;
import com.example.vhs_lab_mihovil.model.Rental;
import com.example.vhs_lab_mihovil.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalDto> rentalDtos = rentals.stream()
                .map(RentalMapper.MAPPER::toDto)
                .collect(Collectors.toList());
        return rentalDtos;
    }

    public Integer insertRental(RentalDto rentalDto) {
        Rental rental = RentalMapper.MAPPER.toModel(rentalDto);

        if (rental.getStartDate() == null) {
            rental.setStartDate(LocalDateTime.now());
        }

        if (rental.getDueDate() == null || rental.getDueDate().isBefore(rental.getStartDate())) {
            rental.setDueDate(rental.getStartDate().plusDays(30));
        }

        rentalRepository.save(rental);
        return rental.getId();
    }

    public Integer updateRental(RentalDto rentalDto) {
        if (rentalDto.getId() == null) {
            throw new IllegalStateException("update.id.null");
        } else {
            Rental rental = RentalMapper.MAPPER.toModel(rentalDto);
            rental = rentalRepository.save(rental);
            return rental.getId();
        }
    }

    public boolean deleteRental(Integer rentalId) {
        if (rentalId == null) {
            throw new IllegalStateException("delete.id.null");
        } else {
            rentalRepository.deleteById(rentalId);
            return true;
        }
    }
}
