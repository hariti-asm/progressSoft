package ma.hariti.asmaa.progresssoft.controller;

import ma.hariti.asmaa.progresssoft.dto.DealRequestDto;
import ma.hariti.asmaa.progresssoft.dto.DealResponseDto;
import ma.hariti.asmaa.progresssoft.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class DealControllerTest {
    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController underTest;

    private DealRequestDto dealRequestDto;
    private DealResponseDto dealResponseDto;

    @BeforeEach
    void setup() {
        dealRequestDto = new DealRequestDto(
                "deal123",
                "MAD",
                "USD",
                LocalDateTime.now(),
                BigDecimal.valueOf(2000)
        );

        dealResponseDto = new DealResponseDto(
                "deal123",
                null,
                null,
                dealRequestDto.dealTimestamp(),
                dealRequestDto.dealAmount()
        );
    }

    @Test
    void whenValidRequest_thenReturnCreatedDealResponse() {
        given(dealService.save(dealRequestDto)).willReturn(dealResponseDto);

        DealResponseDto actual = underTest.createDeal(dealRequestDto);

        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(dealResponseDto);

        verify(dealService).save(dealRequestDto);
    }


    @Test
    void whenDuplicateDealId_thenThrowIllegalStateException() {
        given(dealService.save(dealRequestDto))
                .willThrow(new IllegalStateException("Deal ID already exists"));

        try {
            underTest.createDeal(dealRequestDto);
        } catch (IllegalStateException e) {
            assertThat(e)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Deal ID already exists");
        }

        verify(dealService).save(dealRequestDto);
    }
}
