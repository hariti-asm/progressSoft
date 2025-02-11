package com.progresssoft.repository;

import com.progresssoft.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    boolean existsByFromCurrencyIsoCodeAndToCurrencyIsoCodeAndDealTimestampAndDealAmount(
            String fromCurrencyIsoCode,
            String toCurrencyIsoCode,
            LocalDateTime dealTimestamp,
            BigDecimal dealAmount
    );
}