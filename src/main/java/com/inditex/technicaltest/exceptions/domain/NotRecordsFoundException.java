package com.inditex.technicaltest.exceptions.domain;

import org.springframework.http.HttpStatus;

public class NotRecordsFoundException extends RuntimeException{

  public final static HttpStatus HTTP_STATUS=HttpStatus.NOT_FOUND;

  public NotRecordsFoundException(String message) {
    super(message);
  }
}
