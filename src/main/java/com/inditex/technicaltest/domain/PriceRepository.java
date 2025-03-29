package com.inditex.technicaltest.domain;

import java.util.List;

public interface PriceRepository {

  List<Price> getPricesByChainIdAndProductId(Integer chainId, Integer productId);
}
