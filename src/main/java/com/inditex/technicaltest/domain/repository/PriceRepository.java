package com.inditex.technicaltest.domain.repository;

import com.inditex.technicaltest.domain.model.Price;
import java.util.List;

public interface PriceRepository {

  List<Price> getPricesByChainIdAndProductId(Integer chainId, Integer productId);
}
