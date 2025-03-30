package com.inditex.technicaltest.rates.application.impl;

import static com.inditex.technicaltest.util.Constants.INVALID_PARAMETERS_MESSAGE;
import static com.inditex.technicaltest.util.Constants.LOG_INPUT_PARAMS;
import static com.inditex.technicaltest.util.Constants.NOT_RATES_FOUND_MESSAGE;

import com.inditex.technicaltest.exceptions.domain.BadRequestException;
import com.inditex.technicaltest.exceptions.domain.NotRatesOnDateException;
import com.inditex.technicaltest.rates.application.RatesUseCase;
import com.inditex.technicaltest.rates.domain.model.Rate;
import com.inditex.technicaltest.rates.domain.repository.RateRepository;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class in charge of Managing rate-related use cases
 */
@Service
@RequiredArgsConstructor
public class RatesUseCaseImpl implements RatesUseCase {
  private final RateRepository rateRepository;
  private Logger logger=LoggerFactory.getLogger(RatesUseCaseImpl.class);

  /**
   *Call to the service in charge of recovering the rate to be applied
   * @param brandId The chain identifier
   * @param productId The product identifier
   * @param dateOfApplication The date on which we want to check the rate to be applied
   * @return The rate to be applied
   */
  @Override
  public Rate getRateByDate(Integer brandId, Integer productId, OffsetDateTime dateOfApplication) {
    if(parametersNotValid(brandId,productId,dateOfApplication)){
      throw new BadRequestException(INVALID_PARAMETERS_MESSAGE);
    }
    logger.info(String.format(LOG_INPUT_PARAMS,dateOfApplication,brandId,productId));
    List<Rate> rateList = rateRepository.getPricesByChainIdAndProductId(brandId,productId);
    List<Rate> rateFilterByDate =filterPricesByDate(rateList,dateOfApplication);
    // In case more than one record returns, we must filter by the highest priority value.
    int priority= rateFilterByDate.stream().mapToInt(Rate::getPriority).max().orElse(0);
    return rateFilterByDate.stream().filter
        (p -> p.getPriority()==priority).findFirst().orElseThrow(() ->
        new NotRatesOnDateException(NOT_RATES_FOUND_MESSAGE));
  }

  /**
   * Filtering of retrieved product records by date
   * @param rateList Product records retrieved
   * @param dateOfApplication Date of application
   * @return List of filtered rates
   */
  private List<Rate> filterPricesByDate(List<Rate> rateList, OffsetDateTime dateOfApplication) {
    LocalDateTime dateOfApplicationParsed=dateOfApplication.toLocalDateTime();
    return rateList.stream().filter
        (p -> p.getStartDate().isBefore(dateOfApplicationParsed)
            && p.getEndDate().isAfter(dateOfApplicationParsed)).toList();
  }

  /**
   * Check that the input parameters are valid
   * @param brandId the brand Id
   * @param productId the product Id
   * @param dateOfApplication the date Of Application
   * @return boolean value indicating if they are not valid
   */
  private boolean parametersNotValid(Integer brandId, Integer productId, OffsetDateTime dateOfApplication) {
    return brandId==null || productId==null || dateOfApplication==null;
  }

}
