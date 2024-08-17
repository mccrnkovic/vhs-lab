package com.example.vhs_lab_mihovil.service;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.exception.NoDataFoundException;
import com.example.vhs_lab_mihovil.mapper.RentalMapper;
import com.example.vhs_lab_mihovil.model.Price;
import com.example.vhs_lab_mihovil.model.PriceType;
import com.example.vhs_lab_mihovil.model.Rental;
import com.example.vhs_lab_mihovil.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RentalService {
    private RentalRepository rentalRepository;
    private PriceService priceService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, PriceService priceService) {
        this.rentalRepository = rentalRepository;
        this.priceService = priceService;
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

    public RentalDto rentalReturn(Integer rentalId) throws NoDataFoundException {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isPresent()){
            Rental rental = rentalOptional.get();
            LocalDateTime returnDate = LocalDateTime.now();

            if (returnDate.isAfter(rental.getDueDate())){
                BigDecimal feeAmount = calculate(PriceType.FEE_PER_DAY, rental.getDueDate(), returnDate);
                rental.setFeeAmount(feeAmount);

                BigDecimal rentAmount = calculate(PriceType.RENT_PER_DAY, rental.getStartDate(), rental.getDueDate());
                rental.setRentAmount(rentAmount);
            } else {
                rental.setFeeAmount(BigDecimal.ZERO);
                BigDecimal rentAmount = calculate(PriceType.RENT_PER_DAY, rental.getStartDate(), returnDate);
                rental.setRentAmount(rentAmount);
            }

            rental.setReturnDate(returnDate);
            rental = rentalRepository.save(rental);

            RentalDto updatedRentalDto = RentalMapper.MAPPER.toDto(rental);
            return updatedRentalDto;
        } else {
            //No rental found
            throw new NoDataFoundException(rentalRepository, rentalId);
        }
    }

    private BigDecimal calculate(PriceType priceType, LocalDateTime startDate, LocalDateTime endDate) {
        Price price = priceService.getPriceByType(priceType);
        Duration duration = Duration.between(startDate, endDate);
        long days = duration.plusDays(1).toDays();

        BigDecimal amount = BigDecimal.valueOf(days).multiply(price.getAmount());
        return amount;
    }

}
