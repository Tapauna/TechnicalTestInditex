package com.inditex.technicaltest.rates.domain.repository;

import com.inditex.technicaltest.rates.domain.model.Price;
import java.util.List;

public interface PriceRepository {

  List<Price> getPricesByChainIdAndProductId(Integer chainId, Integer productId);
}
