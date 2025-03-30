package com.inditex.technicaltest.infraestructure.database;

import static com.inditex.technicaltest.util.Constants.NOT_RATES_FOUND_MESSAGE;
import static com.inditex.technicaltest.util.Constants.NOT_RECORDS_FOUND_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.inditex.technicaltest.exceptions.domain.NotRatesOnDateException;
import com.inditex.technicaltest.exceptions.domain.NotRecordsFoundException;
import com.inditex.technicaltest.rates.domain.model.Price;
import com.inditex.technicaltest.rates.infraestructure.database.JpaPriceRepository;
import com.inditex.technicaltest.rates.infraestructure.database.PriceRepositoryImpl;
import com.inditex.technicaltest.rates.infraestructure.database.entity.PriceEntity;
import com.inditex.technicaltest.rates.infraestructure.mapper.PriceMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryImplTest {
  @InjectMocks
  PriceRepositoryImpl priceRepository;
  @Mock
  JpaPriceRepository jpaPriceRepository;
  @Mock
  PriceMapper priceMapper;

  @Test
  public void queryOk(){
    Integer productId=35455;
    Integer chainId=1;
    PriceEntity priceEntity= PriceEntity.builder().build();
    Price price=Price.builder().build();
    when(this.jpaPriceRepository.findByBrandIdAndProductId(any(),any())).
        thenReturn(Optional.of(List.of(priceEntity)));
    when(this.priceMapper.priceEntityListToPriceList(any())).thenReturn(List.of(price));
    List<Price> result=this.priceRepository.getPricesByChainIdAndProductId(chainId,productId);
    Assertions.assertEquals(1,result.size());
  }

  @Test
  public void queryEmpty(){
    Integer productId=35455;
    Integer chainId=1;
    when(this.jpaPriceRepository.findByBrandIdAndProductId(any(),any())).
        thenReturn(Optional.empty());
    Exception exception = assertThrows(NotRecordsFoundException.class, () -> {
      this.priceRepository.getPricesByChainIdAndProductId(chainId,productId);
    });
    assertEquals(NOT_RECORDS_FOUND_MESSAGE, exception.getMessage());
  }
}
