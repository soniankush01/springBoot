package com.excercise.code.exception;

import lombok.Data;

@Data
public class CTSBusinessException extends Exception{

  private int errorCode;

  private String errorMessage;

  public CTSBusinessException(int errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

}
