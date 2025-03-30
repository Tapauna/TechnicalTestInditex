package com.inditex.technicaltest.infraestructure.database;

import com.inditex.technicaltest.domain.model.Price;
import com.inditex.technicaltest.domain.repository.PriceRepository;
import com.inditex.technicaltest.infraestructure.database.entity.PriceEntity;
import com.inditex.technicaltest.infraestructure.mapper.PriceMapper;
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
    if(chainId!=null && productId!=null){
      Optional<List<PriceEntity>> result=jpaRepository.findByBrandIdAndProductId(chainId,productId);
      if(result.isPresent()){
        return priceMapper.priceEntityListToPriceList(result.get());
      }
    }
    return List.of();
  }
}
