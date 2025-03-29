package com.inditex.technicaltest.infraestructure.repository;

import com.inditex.technicaltest.domain.Price;
import com.inditex.technicaltest.domain.PriceRepository;
import com.inditex.technicaltest.infraestructure.entity.PriceEntity;
import com.inditex.technicaltest.infraestructure.mapper.PriceMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryImpl implements PriceRepository {
  private JpaPriceRepository jpaRepository;
  private PriceMapper priceMapper;
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
