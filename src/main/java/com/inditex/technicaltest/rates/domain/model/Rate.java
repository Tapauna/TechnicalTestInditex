package com.inditex.technicaltest.rates.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Rate model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer rateId;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}
