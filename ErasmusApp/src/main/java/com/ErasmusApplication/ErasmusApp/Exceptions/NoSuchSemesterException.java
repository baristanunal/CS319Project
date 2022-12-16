package com.ErasmusApplication.ErasmusApp.Exceptions;

public class NoSuchSemesterException extends RuntimeException{

  public NoSuchSemesterException(String message) {
    super(message);
  }

  public NoSuchSemesterException(String message, Throwable cause) {
    super(message, cause);
  }

}
