package com.progresssoft.service;

import com.progresssoft.dto.DealRequestDto;
import com.progresssoft.dto.DealResponseDto;
import com.progresssoft.entity.Deal;
import com.progresssoft.exception.DealAlreadyExistsException;
import com.progresssoft.mapper.DealMapper;
import com.progresssoft.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class DealServiceImplTest {
    @Mock
    private DealRepository dealRepository;
    @Mock
    private DealMapper dealMapper;

    @InjectMocks
    private DealServiceImpl underTest;
    private DealRequestDto dealRequestDto;
    private Deal deal;

    @BeforeEach
    void setup() {
        dealRequestDto = new DealRequestDto(
                "MAD",
                "USD",
                LocalDateTime.now(),
                BigDecimal.valueOf(2000)
        );

        deal = new Deal(

        );
    }

    @Test
    void whenValidRequest_thenReturnCreatedDeal() {
        given(dealRepository.existsByFromCurrencyIsoCodeAndToCurrencyIsoCodeAndDealTimestampAndDealAmount(
                dealRequestDto.fromCurrencyIsoCode(),
                dealRequestDto.toCurrencyIsoCode(),
                dealRequestDto.dealTimestamp(),
                dealRequestDto.dealAmount()
        )).willReturn(false);

        given(dealMapper.toEntity(dealRequestDto)).willReturn(deal);
        given(dealRepository.save(any(Deal.class))).willReturn(deal);

        DealResponseDto expectedResponse = new DealResponseDto(
                1L,
                dealRequestDto.fromCurrencyIsoCode(),
                dealRequestDto.toCurrencyIsoCode(),
                dealRequestDto.dealTimestamp(),
                dealRequestDto.dealAmount()
        );

        given(dealMapper.toResponseEntity(deal)).willReturn(expectedResponse);

        DealResponseDto actual = underTest.save(dealRequestDto);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isNotNull();
        assertThat(actual.fromCurrencyIsoCode()).isEqualTo(dealRequestDto.fromCurrencyIsoCode());
        assertThat(actual.toCurrencyIsoCode()).isEqualTo(dealRequestDto.toCurrencyIsoCode());
        assertThat(actual.dealTimestamp()).isEqualTo(dealRequestDto.dealTimestamp());
        assertThat(actual.dealAmount()).isEqualTo(dealRequestDto.dealAmount());

        verify(dealRepository).save(any(Deal.class));
    }

    @Test
    void whenDealIdAlreadyExists_thenThrowDealAlreadyExistsException() {
        given(dealRepository.existsByFromCurrencyIsoCodeAndToCurrencyIsoCodeAndDealTimestampAndDealAmount(
                dealRequestDto.fromCurrencyIsoCode(),
                dealRequestDto.toCurrencyIsoCode(),
                dealRequestDto.dealTimestamp(),
                dealRequestDto.dealAmount()
        )).willReturn(true);

        assertThatThrownBy(() -> underTest.save(dealRequestDto))
                .isInstanceOf(DealAlreadyExistsException.class)
                .hasMessage("A deal with the same details already exists");
    }
}