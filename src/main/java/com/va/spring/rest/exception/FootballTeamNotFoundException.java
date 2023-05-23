package com.va.spring.rest.exception;

public class FootballTeamNotFoundException extends Exception {

  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }

  public FootballTeamNotFoundException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

}
