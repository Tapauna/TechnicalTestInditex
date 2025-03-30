package com.inditex.technicaltest.rates.infraestructure.database;

import static com.inditex.technicaltest.util.Constants.NOT_RECORDS_FOUND_MESSAGE;

import com.inditex.technicaltest.exceptions.domain.NotRecordsFoundException;
import com.inditex.technicaltest.rates.domain.model.Rate;
import com.inditex.technicaltest.rates.domain.repository.RateRepository;
import com.inditex.technicaltest.rates.infraestructure.mapper.RateMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RateRepositoryImpl implements RateRepository {
  private final JpaRateRepository jpaRepository;
  private final RateMapper rateMapper;
  /**
   * Call to the database to consult the rates given the chain and the product
   * @param chainId the chain id
   * @param productId the product id
   * @return The list of recovered rates
   */
  @Override
  public List<Rate> getPricesByChainIdAndProductId(Integer chainId, Integer productId) {
    return jpaRepository.findByBrandIdAndProductId(chainId,productId)
        .map(rateMapper::priceEntityListToPriceList)
        .orElseThrow(() ->new NotRecordsFoundException(NOT_RECORDS_FOUND_MESSAGE));
  }
}
