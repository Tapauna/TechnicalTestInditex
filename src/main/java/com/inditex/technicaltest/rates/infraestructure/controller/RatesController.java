package com.inditex.technicaltest.rates.infraestructure.controller;


import com.inditex.technicaltest.rates.application.RatesUseCase;
import com.inditex.technicaltest.rates.infraestructure.mapper.RateMapper;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import org.openapitools.api.GetRatesApi;
import org.openapitools.model.RateResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller in charge of managing tariff-related endpoints
 */
@RestController
@AllArgsConstructor
public class RatesController implements GetRatesApi {

  RatesUseCase ratesUseCase;
  RateMapper rateMapper;

  /**
   *Endpoint in charge of retrieving the rate to be applied given a date for a product in a chain.
   * @param dateOfApplication The date on which we want to check the rate to be applied
   * @param productId The product identifier
   * @param chainId The chain identifier
   * @return Body with product identifier, chain identifier, rate to be applied,
   * dates of application and final price to be applied.
   */
  @Override
  public ResponseEntity<RateResponseDTO> getRates(OffsetDateTime dateOfApplication, Integer productId,
      Integer chainId) {

    return ResponseEntity.ok(rateMapper.priceToRateResponseDTO(
        ratesUseCase.getRateByDate(chainId,productId,dateOfApplication)));
  }
}
