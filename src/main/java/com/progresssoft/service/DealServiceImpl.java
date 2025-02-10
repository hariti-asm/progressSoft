package com.progresssoft.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.progresssoft.dto.DealRequestDto;
import com.progresssoft.dto.DealResponseDto;
import com.progresssoft.entity.Deal;
import com.progresssoft.mapper.DealMapper;
import com.progresssoft.repository.DealRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Override
    public DealResponseDto save(DealRequestDto dto) {
        log.info("Attempting to save deal with ID: {}", dto.dealUniqueId());

        if (dealRepository.existsById(Long.valueOf(dto.dealUniqueId()))) {
            log.warn("Duplicate deal ID detected: {}. Operation aborted.", dto.dealUniqueId());
            throw new IllegalStateException("Deal ID already exists");
        }

        Deal savedDeal = dealRepository.save(dealMapper.toEntity(dto));
        log.info("Deal saved successfully with ID: {}", savedDeal.getId());

        return dealMapper.toResponseEntity(savedDeal);
    }
}
