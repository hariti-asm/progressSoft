package ma.hariti.asmaa.progresssoft.service;

import ma.hariti.asmaa.progresssoft.dto.DealRequestDto;
import ma.hariti.asmaa.progresssoft.dto.DealResponseDto;
import ma.hariti.asmaa.progresssoft.entity.Deal;

public interface DealService {
    DealResponseDto save(DealRequestDto dto);
}