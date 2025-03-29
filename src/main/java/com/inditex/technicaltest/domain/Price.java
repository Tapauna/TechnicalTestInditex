package com.inditex.technicaltest.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Price {
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer rateId;
    private Long productId;
    private Integer priority;
    private Double price;
    private String currency;
}
