package com.inditex.technicaltest.rates.infraestructure.mapper;

import com.inditex.technicaltest.rates.domain.model.Price;
import com.inditex.technicaltest.rates.infraestructure.database.entity.PriceEntity;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.RateResponseDTO;

@Mapper(componentModel = "spring")
public interface PriceMapper {
  List<Price> priceEntityListToPriceList(List<PriceEntity> src);
  Price priceEntityToPrice(PriceEntity src);
  @Mapping(target="startDate", expression = "java(parseToOffsetDateTime(src.getStartDate()))")
  @Mapping(target="endDate", expression = "java(parseToOffsetDateTime(src.getEndDate()))")
  RateResponseDTO priceToRateResponseDTO(Price src);

  default OffsetDateTime parseToOffsetDateTime(LocalDateTime date){
    if(date==null){
      return null;
    }
    ZoneId spainZone = ZoneId.of("Europe/Madrid");
    ZoneOffset offset = spainZone.getRules().getOffset(date);
    return date.atOffset(offset);
  }
}
