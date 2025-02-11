package com.progresssoft.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DealResponseDto(
        Long id,
        String fromCurrencyIsoCode,
        String toCurrencyIsoCode,
        LocalDateTime dealTimestamp,
        BigDecimal dealAmount
) {}