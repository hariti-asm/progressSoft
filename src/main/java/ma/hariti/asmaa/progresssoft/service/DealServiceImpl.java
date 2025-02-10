package ma.hariti.asmaa.progresssoft.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.progresssoft.dto.DealRequestDto;
import ma.hariti.asmaa.progresssoft.dto.DealResponseDto;
import ma.hariti.asmaa.progresssoft.entity.Deal;
import ma.hariti.asmaa.progresssoft.mapper.DealMapper;
import ma.hariti.asmaa.progresssoft.repository.DealRepository;
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

        if (dealRepository.existsById(dto.dealUniqueId())) {
            log.warn("Duplicate deal ID detected: {}. Operation aborted.", dto.dealUniqueId());
            throw new DuplicateDealIdException("Deal id already exists");
        }

        Deal savedDeal = dealRepository.save(dealMapper.toEntity(dto));
        log.info("Deal saved successfully with ID: {}", savedDeal.getId());

        return dealMapper.toResponseEntity(savedDeal);
    }
}