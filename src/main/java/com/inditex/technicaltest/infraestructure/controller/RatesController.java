package com.inditex.technicaltest.infraestructure.controller;


import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import org.openapitools.api.GetRatesApi;
import org.openapitools.model.RateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RatesController implements GetRatesApi {

  private static final Logger logger = LoggerFactory.getLogger(RatesController.class);

  @Override
  public ResponseEntity<RateResponse> getRates(OffsetDateTime dateOfApplication, Integer productId,
      Integer chainId) {
    logger.info("PARAMETROS ENTRADA: " + dateOfApplication + " " + productId + " " + " " + chainId);
    RateResponse response = new RateResponse();
    response.setChainId(chainId);
    response.setProductId(productId);
    return ResponseEntity.ok(response);
  }
}
