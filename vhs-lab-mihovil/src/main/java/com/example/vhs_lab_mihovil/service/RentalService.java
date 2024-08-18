package com.example.vhs_lab_mihovil.service;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.exception.NoDataFoundException;
import com.example.vhs_lab_mihovil.exception.NotDeletedException;
import com.example.vhs_lab_mihovil.exception.VhsUnavailableException;
import com.example.vhs_lab_mihovil.mapper.RentalMapper;
import com.example.vhs_lab_mihovil.model.Price;
import com.example.vhs_lab_mihovil.model.PriceType;
import com.example.vhs_lab_mihovil.model.Rental;
import com.example.vhs_lab_mihovil.model.Vhs;
import com.example.vhs_lab_mihovil.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private RentalRepository rentalRepository;
    private PriceService priceService;
    private VhsService vhsService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, PriceService priceService, VhsService vhsService) {
        this.rentalRepository = rentalRepository;
        this.priceService = priceService;
        this.vhsService = vhsService;
    }

    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalDto> rentalDtos = rentals.stream()
                .map(RentalMapper.MAPPER::toDto)
                .collect(Collectors.toList());
        return rentalDtos;
    }

    private Rental getRentalById(Integer id) throws NoDataFoundException {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            return optionalRental.get();
        } else {
            throw new NoDataFoundException(rentalRepository, id.toString());
        }

    }

    @Transactional
    public RentalDto insertRental(RentalDto rentalDto) throws NoDataFoundException, VhsUnavailableException {
        Vhs vhs = vhsService.getVhsById(rentalDto.getVhsId());
        if (!vhs.getAvailable()) {
            throw new VhsUnavailableException(vhs.getId().toString());
        }

        Rental rental = RentalMapper.MAPPER.toModel(rentalDto);
        if (rental.getStartDate() == null) {
            rental.setStartDate(LocalDateTime.now());
        }

        if (rental.getDueDate() == null || rental.getDueDate().isBefore(rental.getStartDate())) {
            rental.setDueDate(rental.getStartDate().plusDays(30));
        }

        rental = rentalRepository.save(rental);
        if (rental.getId() != null) {
            vhsService.updateAvailability(rental.getVhs().getId(), false);
        }

        RentalDto newRentalDto = RentalMapper.MAPPER.toDto(rental);
        return newRentalDto;
    }

    @Transactional
    public RentalDto updateRental(RentalDto rentalDto) throws NoDataFoundException {
        if (rentalDto.getId() == null) {
            throw new IllegalStateException("update.id.null");
        } else {
            Rental rental = getRentalById(rentalDto.getId());
            Rental newRental = RentalMapper.MAPPER.toModel(rentalDto);
            newRental.setVhs(rental.getVhs());

            newRental = rentalRepository.save(newRental);
            RentalDto updatedRentalDto = RentalMapper.MAPPER.toDto(newRental);
            return updatedRentalDto;
        }
    }

    @Transactional
    public boolean deleteRental(Integer rentalId) throws NotDeletedException {
        if (rentalId == null) {
            throw new IllegalStateException("delete.id.null");
        } else {
            int deleted = rentalRepository.deleteRentalById(rentalId);
            if (deleted == 0) {
                throw new NotDeletedException(rentalRepository, rentalId.toString());
            }
            return true;
        }
    }

    @Transactional
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

            vhsService.updateAvailability(rental.getVhs().getId(), true);

            RentalDto updatedRentalDto = RentalMapper.MAPPER.toDto(rental);
            return updatedRentalDto;
        } else {
            //No rental found
            throw new NoDataFoundException(rentalRepository, rentalId.toString());
        }
    }

    private BigDecimal calculate(PriceType priceType, LocalDateTime startDate, LocalDateTime endDate) throws NoDataFoundException {
        Price price = priceService.getPriceByType(priceType);
        Duration duration = Duration.between(startDate, endDate);
        long days = duration.plusDays(1).toDays();

        BigDecimal amount = BigDecimal.valueOf(days).multiply(price.getAmount());
        return amount;
    }

}
