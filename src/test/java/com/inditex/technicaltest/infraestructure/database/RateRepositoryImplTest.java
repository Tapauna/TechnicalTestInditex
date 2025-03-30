package com.inditex.technicaltest.infraestructure.database;

import static com.inditex.technicaltest.util.Constants.NOT_RECORDS_FOUND_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.inditex.technicaltest.exceptions.domain.NotRecordsFoundException;
import com.inditex.technicaltest.rates.domain.model.Rate;
import com.inditex.technicaltest.rates.infraestructure.database.JpaRateRepository;
import com.inditex.technicaltest.rates.infraestructure.database.RateRepositoryImpl;
import com.inditex.technicaltest.rates.infraestructure.database.entity.RateEntity;
import com.inditex.technicaltest.rates.infraestructure.mapper.RateMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RateRepositoryImplTest {
  @InjectMocks
  RateRepositoryImpl priceRepository;
  @Mock
  JpaRateRepository jpaRateRepository;
  @Mock
  RateMapper rateMapper;

  @Test
  public void queryOk(){
    Integer productId=35455;
    Integer chainId=1;
    RateEntity rateEntity = RateEntity.builder().build();
    Rate rate = Rate.builder().build();
    when(this.jpaRateRepository.findByBrandIdAndProductId(any(),any())).
        thenReturn(Optional.of(List.of(rateEntity)));
    when(this.rateMapper.priceEntityListToPriceList(any())).thenReturn(List.of(rate));
    List<Rate> result=this.priceRepository.getPricesByChainIdAndProductId(chainId,productId);
    Assertions.assertEquals(1,result.size());
  }

  @Test
  public void queryEmpty(){
    Integer productId=35455;
    Integer chainId=1;
    when(this.jpaRateRepository.findByBrandIdAndProductId(any(),any())).
        thenReturn(Optional.empty());
    Exception exception = assertThrows(NotRecordsFoundException.class, () -> {
      this.priceRepository.getPricesByChainIdAndProductId(chainId,productId);
    });
    assertEquals(NOT_RECORDS_FOUND_MESSAGE, exception.getMessage());
  }
}
