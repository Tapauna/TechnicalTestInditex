package com.inditex.technicaltest.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.inditex.technicaltest.rates.application.RatesUseCase;
import com.inditex.technicaltest.rates.domain.model.Rate;
import com.inditex.technicaltest.rates.infraestructure.controller.RatesController;
import com.inditex.technicaltest.rates.infraestructure.mapper.RateMapper;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.RateResponseDTO;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RatesControllerTest {

  @InjectMocks
  private RatesController ratesController;
  @Mock
  private RatesUseCase ratesUseCase;
  @Mock
  private RateMapper rateMapper;

  @Test
  void getRatesTestOk(){
    Integer productId=35455;
    Integer chainId=1;
    OffsetDateTime dateOfApplication=OffsetDateTime.now();
    RateResponseDTO responseDTO=new RateResponseDTO();
    when(this.ratesUseCase.getRateByDate(any(),any(),any())).thenReturn(
        Rate.builder().build());
    when(this.rateMapper.priceToRateResponseDTO(any())).thenReturn(responseDTO);
    ResponseEntity<RateResponseDTO> response=this.ratesController.getRates(dateOfApplication,chainId,productId);
    assertEquals(200,response.getStatusCode().value());
    assertEquals(responseDTO,response.getBody());
  }
}
