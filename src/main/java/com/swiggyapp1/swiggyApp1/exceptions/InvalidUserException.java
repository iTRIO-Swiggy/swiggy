package com.swiggyapp1.swiggyApp1.exceptions;

public class InvalidUserException extends RuntimeException {
  public InvalidUserException(String message) {
    super(message);
  }
}
