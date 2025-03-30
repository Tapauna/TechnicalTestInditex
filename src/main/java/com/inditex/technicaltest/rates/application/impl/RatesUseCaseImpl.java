package com.inditex.technicaltest.rates.application.impl;

import static com.inditex.technicaltest.util.Constants.INVALID_PARAMETERS_MESSAGE;
import static com.inditex.technicaltest.util.Constants.LOG_INPUT_PARAMS;
import static com.inditex.technicaltest.util.Constants.NOT_RATES_FOUND_MESSAGE;

import com.inditex.technicaltest.exceptions.domain.BadRequestException;
import com.inditex.technicaltest.exceptions.domain.NotRatesOnDateException;
import com.inditex.technicaltest.rates.application.RatesUseCase;
import com.inditex.technicaltest.rates.domain.model.Price;
import com.inditex.technicaltest.rates.domain.repository.PriceRepository;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatesUseCaseImpl implements RatesUseCase {
  private final PriceRepository priceRepository;
  private Logger logger=LoggerFactory.getLogger(RatesUseCaseImpl.class);

  @Override
  public Price getRateByDate(Integer brandId, Integer productId, OffsetDateTime dateOfApplication) {
    if(parametersNotValid(brandId,productId,dateOfApplication)){
      throw new BadRequestException(INVALID_PARAMETERS_MESSAGE);
    }
    logger.info(String.format(LOG_INPUT_PARAMS,dateOfApplication,brandId,productId));
    List<Price> priceList=priceRepository.getPricesByChainIdAndProductId(brandId,productId);
    List<Price> priceFilterByDate=filterPricesByDate(priceList,dateOfApplication);
    int priority=priceFilterByDate.stream().mapToInt(Price::getPriority).max().orElse(0);
    return priceFilterByDate.stream().filter
        (p -> p.getPriority()==priority).findFirst().orElseThrow(() ->
        new NotRatesOnDateException(NOT_RATES_FOUND_MESSAGE));
  }

  private List<Price> filterPricesByDate(List<Price> priceList, OffsetDateTime dateOfApplication) {
    LocalDateTime dateOfApplicationParsed=dateOfApplication.toLocalDateTime();
    return priceList.stream().filter
        (p -> p.getStartDate().isBefore(dateOfApplicationParsed)
            && p.getEndDate().isAfter(dateOfApplicationParsed)).toList();
  }

  private boolean parametersNotValid(Integer brandId, Integer productId, OffsetDateTime dateOfApplication) {
    return brandId==null || productId==null || dateOfApplication==null;
  }

}
