package com.inditex.technicaltest.rates.domain.repository;

import com.inditex.technicaltest.rates.domain.model.Rate;
import java.util.List;

public interface RateRepository {

  /**
   * Call to the database to consult the rates given the chain and the product
   * @param chainId the chain id
   * @param productId the product id
   * @return The list of recovered rates
   */
  List<Rate> getPricesByChainIdAndProductId(Integer chainId, Integer productId);
}
