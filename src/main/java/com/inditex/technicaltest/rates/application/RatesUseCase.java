package com.inditex.technicaltest.rates.application;

import com.inditex.technicaltest.rates.domain.model.Rate;
import java.time.OffsetDateTime;

public interface RatesUseCase {

  /**
   *Call to the service in charge of recovering the rate to be applied
   * @param brandId The chain identifier
   * @param productId The product identifier
   * @param dateOfApplication The date on which we want to check the rate to be applied
   * @return The rate to be applied
   */
  Rate getRateByDate(Integer brandId,Integer productId, OffsetDateTime dateOfApplication);
}
