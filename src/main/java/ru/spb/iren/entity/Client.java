package ru.spb.iren.entity;

import ru.spb.iren.entity.toy.Toy;
import ru.spb.iren.service.writer.ClientWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Посетитель магазина. Играет в викторину игрушек
 */
public final class Client {
    /**
     * Список игрушек, которые выиграл клиент
     */
    private final List<Toy> wonToys;
    private final String name;
    private final int drawTries;
    private final ClientWriter clientWriter;

    public Client(String name, int drawTries) throws IOException {
        this.wonToys = new ArrayList<>();
        this.name = name;
        this.drawTries = drawTries;
        this.clientWriter = new ClientWriter(this);
    }

    public int getDrawTries() {
        return drawTries;
    }

    public float draw() {
        return new Random().nextFloat();
    }

    public List<Toy> getWonToys() {
        return wonToys;
    }

    public void addWinToy(Toy toy) throws IOException {
        Optional<Toy> first = wonToys.stream()
                .filter(t -> t.getTypeName().equals(toy.getTypeName()))
                .findFirst();
        if (first.isPresent()) {
            Toy toy1 = first.get();
            toy1.setQuantity(toy1.getQuantity() + 1);
        } else
            wonToys.add(toy.copy().setQuantity(1));

        clientWriter.write();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Клиент с именем: " + name + " и игрушками: " + wonToys;
    }
}
