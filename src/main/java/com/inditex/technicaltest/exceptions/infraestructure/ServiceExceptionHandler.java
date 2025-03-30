package com.inditex.technicaltest.exceptions.infraestructure;

import com.inditex.technicaltest.exceptions.domain.BadRequestException;
import com.inditex.technicaltest.exceptions.domain.NotRatesOnDateException;
import com.inditex.technicaltest.exceptions.domain.NotRecordsFoundException;
import org.openapitools.model.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

  @ExceptionHandler(value =BadRequestException.class)
  public ResponseEntity<ErrorDTO> badResquestExceptionHandler(BadRequestException ex){
    ErrorDTO errorDTO= new ErrorDTO(BadRequestException.HTTP_STATUS.value(),ex.getMessage());
    return new ResponseEntity<>(errorDTO,BadRequestException.HTTP_STATUS);
  }

  @ExceptionHandler(value = NotRecordsFoundException.class)
  public ResponseEntity<ErrorDTO> notRecordsFoundExceptionHandler(NotRecordsFoundException ex){
    ErrorDTO errorDTO= new ErrorDTO(NotRecordsFoundException.HTTP_STATUS.value(),ex.getMessage());
    return new ResponseEntity<>(errorDTO,NotRecordsFoundException.HTTP_STATUS);
  }

  @ExceptionHandler(value = NotRatesOnDateException.class)
  public ResponseEntity<ErrorDTO> notRatesOnDateExceptionHandler(NotRatesOnDateException ex){
    ErrorDTO errorDTO= new ErrorDTO(NotRatesOnDateException.HTTP_STATUS.value(),ex.getMessage());
    return new ResponseEntity<>(errorDTO,NotRatesOnDateException.HTTP_STATUS);
  }
}
