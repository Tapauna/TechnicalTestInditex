package com.inditex.technicaltest.rates.domain.repository;

import com.inditex.technicaltest.rates.domain.model.Rate;
import java.util.List;

public interface RateRepository {

  List<Rate> getPricesByChainIdAndProductId(Integer chainId, Integer productId);
}
