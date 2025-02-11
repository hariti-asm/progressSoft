package com.progresssoft.service;

import com.progresssoft.exception.DealAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.progresssoft.dto.DealRequestDto;
import com.progresssoft.dto.DealResponseDto;
import com.progresssoft.entity.Deal;
import com.progresssoft.mapper.DealMapper;
import com.progresssoft.repository.DealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Override
    @Transactional
    public DealResponseDto save(DealRequestDto dto) {
        log.info("Attempting to save deal: {}", dto);

        if (isDealExists(dto)) {
            log.warn("Duplicate deal detected: {}", dto);
            throw new DealAlreadyExistsException("A deal with the same details already exists");
        }

        Deal savedDeal = dealRepository.save(dealMapper.toEntity(dto));
        log.info("Deal saved successfully with ID: {}", savedDeal);

        return dealMapper.toResponseEntity(savedDeal);
    }

    private boolean isDealExists(DealRequestDto dto) {
        return dealRepository.existsByFromCurrencyIsoCodeAndToCurrencyIsoCodeAndDealTimestampAndDealAmount(
                dto.fromCurrencyIsoCode(),
                dto.toCurrencyIsoCode(),
                dto.dealTimestamp(),
                dto.dealAmount()
        );
    }
}