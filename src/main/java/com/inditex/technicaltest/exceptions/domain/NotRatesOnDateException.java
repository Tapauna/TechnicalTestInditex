package com.inditex.technicaltest.exceptions.domain;

import org.springframework.http.HttpStatus;

public class NotRatesOnDateException extends RuntimeException{

  public final static HttpStatus HTTP_STATUS=HttpStatus.NOT_FOUND;

  public NotRatesOnDateException(String message) {
    super(message);
  }
}
