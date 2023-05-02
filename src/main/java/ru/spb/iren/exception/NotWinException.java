package ru.spb.iren.exception;

public class NotWinException extends Exception{
    public NotWinException() {
        super("Не удалось выиграть игрушку");
    }
}
