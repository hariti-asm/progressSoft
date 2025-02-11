package com.progresssoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "deals")

@Getter
@Setter
@RequiredArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3)
    private String fromCurrencyIsoCode;

    @Column(length = 3)
    private String toCurrencyIsoCode;

    private LocalDateTime dealTimestamp;

    private BigDecimal dealAmount;
}
