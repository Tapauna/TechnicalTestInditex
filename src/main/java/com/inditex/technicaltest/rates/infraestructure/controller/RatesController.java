package com.inditex.technicaltest.rates.infraestructure.controller;


import com.inditex.technicaltest.rates.application.RatesUseCase;
import com.inditex.technicaltest.rates.infraestructure.mapper.RateMapper;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import org.openapitools.api.GetRatesApi;
import org.openapitools.model.RateResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RatesController implements GetRatesApi {

  RatesUseCase ratesUseCase;
  RateMapper rateMapper;

  @Override
  public ResponseEntity<RateResponseDTO> getRates(OffsetDateTime dateOfApplication, Integer productId,
      Integer chainId) {

    return ResponseEntity.ok(rateMapper.priceToRateResponseDTO(
        ratesUseCase.getRateByDate(chainId,productId,dateOfApplication)));
  }
}
