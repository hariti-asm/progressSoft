package ma.hariti.asmaa.progresssoft.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record DealResponseDto(
        @NotBlank(message = "ID cannot be blank")
        String id,

        @NotNull(message = "From Currency cannot be null")
        @Valid
        Currency fromCurrency,

        @NotNull(message = "To Currency cannot be null")
        @Valid
        Currency toCurrency,

        @NotNull(message = "Timestamp cannot be null")
        @PastOrPresent(message = "Timestamp must be in past or present")
        LocalDateTime timestamp,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
        BigDecimal amount
) {}