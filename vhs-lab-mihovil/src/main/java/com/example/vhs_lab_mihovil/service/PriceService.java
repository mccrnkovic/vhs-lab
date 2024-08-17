package com.example.vhs_lab_mihovil.service;

import com.example.vhs_lab_mihovil.dto.PriceDto;
import com.example.vhs_lab_mihovil.exception.NoDataFoundException;
import com.example.vhs_lab_mihovil.mapper.PriceMapper;
import com.example.vhs_lab_mihovil.model.Price;
import com.example.vhs_lab_mihovil.model.PriceType;
import com.example.vhs_lab_mihovil.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceService {

    private PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<PriceDto> getAllPrices(){
        List<Price> prices = priceRepository.findAll();
        List<PriceDto> priceDtos = prices.stream()
                .map(PriceMapper.MAPPER::toDto)
                .collect(Collectors.toList());
        return priceDtos;
    }

    public Price getPriceByType(PriceType priceType) throws NoDataFoundException {
        Optional<Price> priceOptional = priceRepository.findById(priceType);
        if (priceOptional.isPresent()) {
            return priceOptional.get();
        } else {
            throw new NoDataFoundException(priceRepository, priceType.name());
        }
    }

    public PriceType updatePrice(PriceDto priceDto) {
        if (priceDto.getId() == null) {
            throw new IllegalStateException("update.id.null");
        }
        Price price = PriceMapper.MAPPER.toModel(priceDto);
        priceRepository.save(price);
        return price.getId();
    }
}
