package com.inditex.technicaltest.rates.infraestructure.database;

import static com.inditex.technicaltest.util.Constants.NOT_RECORDS_FOUND_MESSAGE;

import com.inditex.technicaltest.exceptions.domain.NotRecordsFoundException;
import com.inditex.technicaltest.rates.domain.model.Price;
import com.inditex.technicaltest.rates.domain.repository.PriceRepository;
import com.inditex.technicaltest.rates.infraestructure.database.entity.PriceEntity;
import com.inditex.technicaltest.rates.infraestructure.mapper.PriceMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {
  private final JpaPriceRepository jpaRepository;
  private final PriceMapper priceMapper;
  @Override
  public List<Price> getPricesByChainIdAndProductId(Integer chainId, Integer productId) {
    return jpaRepository.findByBrandIdAndProductId(chainId,productId)
        .map(priceMapper::priceEntityListToPriceList)
        .orElseThrow(() ->new NotRecordsFoundException(NOT_RECORDS_FOUND_MESSAGE));
  }
}
