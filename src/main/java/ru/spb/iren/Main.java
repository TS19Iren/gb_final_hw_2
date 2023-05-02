package ru.spb.iren;

import ru.spb.iren.entity.*;
import ru.spb.iren.entity.toy.Toy;
import ru.spb.iren.exception.NoToyForPrizeException;
import ru.spb.iren.service.market.IMarketV2;
import ru.spb.iren.service.market.YetAnotherToyMarket;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Client cl = new Client("Iren", 10);
        Client cl2 = new Client("Iren2", 30);
        IMarketV2<Toy> marketV2 = new YetAnotherToyMarket();
        System.out.println("Разыгрывается " + marketV2.getToyForQuiz());
        try {
            marketV2.quiz(cl);

            marketV2.quiz(cl2);
        } catch (NoToyForPrizeException e) {
            System.out.println("Все игрушки разыграны");
        }
        System.out.printf("Клиент %s и его \"трофеи\": %s\n", cl.getName(), cl.getWonToys());

        System.out.printf("Клиент %s и его \"трофеи\": %s\n", cl2.getName(), cl2.getWonToys());

    }
}
