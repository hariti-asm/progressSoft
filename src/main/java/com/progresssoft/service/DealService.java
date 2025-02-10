package com.progresssoft.service;

import com.progresssoft.dto.DealRequestDto;
import com.progresssoft.dto.DealResponseDto;

public interface DealService {
    DealResponseDto save(DealRequestDto dto);
}