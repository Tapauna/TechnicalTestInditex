package com.inditex.technicaltest.application;

import static com.inditex.technicaltest.util.Constants.INVALID_PARAMETERS_MESSAGE;
import static com.inditex.technicaltest.util.Constants.NOT_RATES_FOUND_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.inditex.technicaltest.exceptions.domain.BadRequestException;
import com.inditex.technicaltest.exceptions.domain.NotRatesOnDateException;
import com.inditex.technicaltest.rates.application.impl.RatesUseCaseImpl;
import com.inditex.technicaltest.rates.domain.model.Rate;
import com.inditex.technicaltest.rates.domain.repository.RateRepository;
import com.inditex.technicaltest.rates.infraestructure.mapper.RateMapper;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RatesUseCaseImplTest {

  @InjectMocks
  private RatesUseCaseImpl ratesUseCase;

  @Mock
  private RateMapper rateMapper;

  @Mock
  private RateRepository rateRepository;

  private Rate rate;

  private LocalDateTime dateNow;

  private OffsetDateTime dateOfApplication;

  @BeforeEach
  public void setUp(){
    Integer productId=35455;
    Integer chainId=1;
    this.dateNow=LocalDateTime.now().plusDays(1);
    this.dateOfApplication=dateNow.atOffset(ZoneOffset.UTC);
    this.rate = Rate.builder().startDate(dateNow.minusDays(1))
        .endDate(dateNow.plusDays(2))
        .brandId(chainId)
        .productId(productId)
        .priority(0)
        .build();
  }
  @Test
  public void getRateByDateOk(){
    Integer productId=35455;
    Integer chainId=1;
    LocalDateTime dateNow=LocalDateTime.now().plusDays(1);
    when(this.rateRepository.getPricesByChainIdAndProductId(any(),any())).thenReturn(List.of(rate));
    Rate result=this.ratesUseCase.getRateByDate(productId,chainId,this.dateOfApplication);
    assertEquals(rate,result);
  }

  @Test
  public void getRateByDateInvalidData(){
    Integer productId=35455;
    Integer chainId=null;
    Exception exception = assertThrows(BadRequestException.class, () -> {
      this.ratesUseCase.getRateByDate(productId,chainId,dateOfApplication);
    });
    assertEquals(INVALID_PARAMETERS_MESSAGE, exception.getMessage());
  }

  @Test
  public void getRateByDateNoRatesOnDate(){
    Integer productId=35455;
    Integer chainId=1;
    this.dateOfApplication=this.dateNow.plusDays(5).atOffset(ZoneOffset.UTC);
    Exception exception = assertThrows(NotRatesOnDateException.class, () -> {
      this.ratesUseCase.getRateByDate(productId,chainId,dateOfApplication);
    });
    assertEquals(NOT_RATES_FOUND_MESSAGE, exception.getMessage());
  }
}
