package com.inditex.technicaltest.rates.application;

import com.inditex.technicaltest.rates.domain.model.Price;
import java.time.OffsetDateTime;
import java.util.Optional;

public interface RatesUseCase {

  Price getRateByDate(Integer brandId,Integer productId, OffsetDateTime dateOfApplication);
}
