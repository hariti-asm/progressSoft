package ma.hariti.asmaa.progresssoft.entity;

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
@AllArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deal_unique_id", nullable = false)
    private String dealUniqueId;

    @Column(name = "from_currency", length = 3, nullable = false)
    private String fromCurrencyIsoCode;

    @Column(name = "to_currency", length = 3, nullable = false)
    private String toCurrencyIsoCode;

    @Column(name = "deal_timestamp", nullable = false)
    private LocalDateTime dealTimestamp;

    @Column(name = "deal_amount", nullable = false)
    private BigDecimal dealAmount;
}
