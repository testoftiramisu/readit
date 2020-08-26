package io.testoftiramisu.readit.exceptions;

public class ReaditExeption extends RuntimeException {
  public ReaditExeption(String message, Exception exception) {
    super(message, exception);
  }

  public ReaditExeption(String message) {
    super(message);
  }
}
