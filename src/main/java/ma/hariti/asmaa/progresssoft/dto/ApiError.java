package ma.hariti.asmaa.progresssoft.dto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(
        HttpStatus status,
        String message,
        String details,
        LocalDateTime timestamp
) {}