package ru.spb.iren.service.market;

import ru.spb.iren.entity.Client;
import ru.spb.iren.entity.toy.Plush;
import ru.spb.iren.entity.toy.Robot;
import ru.spb.iren.entity.toy.SomeRandomToy;
import ru.spb.iren.entity.toy.Toy;
import ru.spb.iren.exception.NoToyForPrizeException;
import ru.spb.iren.exception.NotWinException;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class YetAnotherToyMarket implements IMarketV2<Toy> {
    private final List<Toy> marketToys;
    private final Toy quizToy;

    public YetAnotherToyMarket() {
        this.marketToys = generateToyForMarket();
        Random random = new Random();
        this.quizToy = marketToys.get(random.nextInt(marketToys.size()));
    }

    @Override
    public List<Toy> getAllToys() {
        return marketToys;
    }

    @Override
    public Toy getToyForQuiz() {
        return quizToy;
    }

    @Override
    public void quiz(Client cl) throws NoToyForPrizeException {
        Toy toysForQuiz = getToyForQuiz();
        for (int i = 0; i < cl.getDrawTries(); i++) {
            float draw = cl.draw();
            try {
                if (toysForQuiz.getProbability() < draw) {
                    cl.addWinToy(toysForQuiz);
                    printGreen("%s выбросил %s и выиграл игрушку %s\n".formatted(cl.getName(), draw, toysForQuiz.getName()));
                    toysForQuiz.setQuantity(toysForQuiz.getQuantity() - 1);
                    if (toysForQuiz.getQuantity() == 0)
                        throw new NoToyForPrizeException();
                } else throw new NotWinException();
            } catch (NotWinException e) {
                printRed("\u001B[31m%s выбросил %s и не смог выиграть игрушку %s\n\u001B[0m".formatted(cl.getName(), draw, toysForQuiz.getName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**********************/
    private List<Toy> generateToyForMarket() {
        final int MAX_TOYS_COUNT_PER_GROUP = 10;
        final Random rnd = new Random();
        return List.of(
                new Robot().setQuantity(rnd.nextInt(MAX_TOYS_COUNT_PER_GROUP)),
                new Plush().setQuantity(rnd.nextInt(MAX_TOYS_COUNT_PER_GROUP)),
                new SomeRandomToy("Toy1").setQuantity(rnd.nextInt(MAX_TOYS_COUNT_PER_GROUP)),
                new SomeRandomToy("Toy2").setQuantity(rnd.nextInt(MAX_TOYS_COUNT_PER_GROUP)),
                new Toy() {
                    @Override
                    public String getTypeName() {
                        return "Cat";
                    }
                }.setName("Kitty").setQuantity(rnd.nextInt(MAX_TOYS_COUNT_PER_GROUP)));
    }

    public static void printRed(String text) {
        System.out.println("\u001B[31m" + text + "\u001B[0m");
    }

    public static void printGreen(String text) {
        System.out.println("\u001B[32m" + text + "\u001B[0m");
    }
}
