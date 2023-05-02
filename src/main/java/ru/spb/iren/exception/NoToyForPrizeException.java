package ru.spb.iren.exception;

public class NoToyForPrizeException extends Exception {
  public NoToyForPrizeException() {
    super("Нет игрушек для викторины");
  }
}
