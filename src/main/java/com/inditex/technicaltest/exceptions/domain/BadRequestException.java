package com.inditex.technicaltest.exceptions.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BadRequestException extends RuntimeException{

  public final static HttpStatus HTTP_STATUS=HttpStatus.BAD_REQUEST;

  public BadRequestException(String message) {
    super(message);
  }
}
