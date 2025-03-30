package com.inditex.technicaltest.rates.infraestructure.controller;


import com.inditex.technicaltest.rates.domain.model.Price;
import com.inditex.technicaltest.rates.application.RatesUseCase;
import com.inditex.technicaltest.rates.infraestructure.mapper.PriceMapper;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.openapitools.api.GetRatesApi;
import org.openapitools.model.RateResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RatesController implements GetRatesApi {

  RatesUseCase ratesUseCase;
  PriceMapper priceMapper;

  @Override
  public ResponseEntity<RateResponseDTO> getRates(OffsetDateTime dateOfApplication, Integer productId,
      Integer chainId) {

    return ResponseEntity.ok(priceMapper.priceToRateResponseDTO(
        ratesUseCase.getRateByDate(chainId,productId,dateOfApplication)));
  }
}
