package com.example.vhs_lab_mihovil;

import com.example.vhs_lab_mihovil.controller.RentalController;
import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.exception.NoDataFoundException;
import com.example.vhs_lab_mihovil.exception.NotDeletedException;
import com.example.vhs_lab_mihovil.exception.VhsUnavailableException;
import com.example.vhs_lab_mihovil.model.Vhs;
import com.example.vhs_lab_mihovil.repository.RentalRepository;
import com.example.vhs_lab_mihovil.service.RentalService;
import com.example.vhs_lab_mihovil.service.VhsService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RentalTest {

    @Autowired
    RentalController rentalController;
    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    VhsService vhsService;

    private RentalDto createRentalDto(Integer userId, Integer vhsId) {
        RentalDto rentalDto = new RentalDto();

        rentalDto.setUserId(userId);
        rentalDto.setVhsId(vhsId);

        return rentalDto;
    };

    @Test
    void rentVhsTest() throws VhsUnavailableException, NoDataFoundException, NotDeletedException {
        RentalDto newRentalDto = createRentalDto(1, 1); //Using preloaded data from data.sql file

        ResponseEntity response = rentalController.insertRental(newRentalDto);
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody() instanceof RentalDto);

        RentalDto insertedRentalDto = (RentalDto) response.getBody();
        assertNotNull(insertedRentalDto.getId());
    }

    @Test
    void vhsUnavailableTest() throws NoDataFoundException, VhsUnavailableException {
        RentalDto newRentalDto = createRentalDto(1, 2);
        ResponseEntity response = rentalController.insertRental(newRentalDto);
        RentalDto insertedRentalDto = (RentalDto) response.getBody();

        VhsUnavailableException e = assertThrows(VhsUnavailableException.class, () -> rentalController.insertRental(newRentalDto));
        assertTrue(e.getVhsId().equals(insertedRentalDto.getVhsId().toString()));
    }

    @Test
    void returnVhsTest() throws NoDataFoundException, VhsUnavailableException {
        RentalDto rentalDto = createRentalDto(1, 3);
        rentalDto = (RentalDto) rentalController.insertRental(rentalDto).getBody();

        ResponseEntity response = rentalController.rentalReturn(rentalDto.getId());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));

        Vhs vhs = vhsService.getVhsById(rentalDto.getVhsId());
        assertTrue(vhs.getAvailable());
    }

    @AfterAll
    public void clearData() {
        rentalRepository.deleteAll();
    }
}
