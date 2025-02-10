package ma.hariti.asmaa.progresssoft.service;

import ma.hariti.asmaa.progresssoft.dto.DealRequestDto;
import ma.hariti.asmaa.progresssoft.dto.DealResponseDto;
import ma.hariti.asmaa.progresssoft.entity.Deal;
import ma.hariti.asmaa.progresssoft.mapper.DealMapper;
import ma.hariti.asmaa.progresssoft.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private DealService underTest;

    private DealRequestDto dealRequestDto;
    private Deal deal;

    @BeforeEach
    void setup() {
        underTest = new DealServiceImpl(dealRepository, dealMapper);
        dealRequestDto = new DealRequestDto(
                "deal123",
                "MAD",
                "USD",
                LocalDateTime.now(),
                BigDecimal.valueOf(2000)
        );

        deal = new Deal(
                null,
                dealRequestDto.dealUniqueId(),
                dealRequestDto.fromCurrencyIsoCode(),
                dealRequestDto.toCurrencyIsoCode(),
                dealRequestDto.dealTimestamp(),
                dealRequestDto.dealAmount()
        );
    }

    @Test
    void whenValidRequest_thenReturnCreatedDeal() {
        given(dealRepository.existsById(dealRequestDto.dealUniqueId())).willReturn(false);
        given(dealMapper.toEntity(dealRequestDto)).willReturn(deal);
        given(dealRepository.save(any(Deal.class))).willReturn(deal);

        DealResponseDto expectedResponse = new DealResponseDto(
                dealRequestDto.dealUniqueId(),
                null,
                null,
                dealRequestDto.dealTimestamp(),
                dealRequestDto.dealAmount()
        );
        given(dealMapper.toResponseEntity(deal)).willReturn(expectedResponse);

        DealResponseDto actual = underTest.save(dealRequestDto);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(dealRequestDto.dealUniqueId());
        verify(dealRepository).save(any(Deal.class));
    }

    @Test
    void whenDealIdAlreadyExists_thenThrowIllegalStateException() {
        given(dealRepository.existsById(dealRequestDto.dealUniqueId())).willReturn(true);

        assertThatThrownBy(() -> underTest.save(dealRequestDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Deal ID already exists");
    }
}
