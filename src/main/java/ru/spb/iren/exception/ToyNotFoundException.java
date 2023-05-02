package ru.spb.iren.exception;

public class ToyNotFoundException extends Exception {
  public ToyNotFoundException(String type) {
    super("Не удалось найти игрушку с типом: " + type);
  }
}
