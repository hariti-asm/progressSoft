package ma.hariti.asmaa.progresssoft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
public record DealRequestDto(
        @NotBlank(message = "Deal Unique ID cannot be blank")
        String dealUniqueId,

        @Pattern(regexp = "^[A-Z]{3}$", message = "From Currency ISO Code must be 3 uppercase letters")
        @NotBlank(message = "From Currency ISO Code is required")
        String fromCurrencyIsoCode,

        @Pattern(regexp = "^[A-Z]{3}$", message = "To Currency ISO Code must be 3 uppercase letters")
        @NotBlank(message = "To Currency ISO Code is required")
        String toCurrencyIsoCode,

        @NotNull(message = "Deal Timestamp cannot be null")
        @PastOrPresent(message = "Deal Timestamp must be in past or present")
        LocalDateTime dealTimestamp,

        @NotNull(message = "Deal Amount cannot be null")
        @Positive(message = "Deal Amount must be positive")
        BigDecimal dealAmount
) {}