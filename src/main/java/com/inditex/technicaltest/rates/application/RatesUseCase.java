package com.inditex.technicaltest.rates.application;

import com.inditex.technicaltest.rates.domain.model.Rate;
import java.time.OffsetDateTime;

public interface RatesUseCase {

  Rate getRateByDate(Integer brandId,Integer productId, OffsetDateTime dateOfApplication);
}
