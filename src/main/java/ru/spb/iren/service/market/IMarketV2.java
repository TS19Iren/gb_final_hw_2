package ru.spb.iren.service.market;

import ru.spb.iren.entity.Client;
import ru.spb.iren.entity.toy.Toy;
import ru.spb.iren.exception.NoToyForPrizeException;

import java.util.List;

public interface IMarketV2<T extends Toy> {
    List<T> getAllToys();

    /**
     * Игрушка для розыгрыша
     *
     */
    T getToyForQuiz();

    /**
     * Розыгрыш игрушек
     */
    void quiz(Client cl) throws NoToyForPrizeException;


}
