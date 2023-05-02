package ru.spb.iren.service.writer;

import ru.spb.iren.entity.Client;
import ru.spb.iren.entity.toy.Toy;

import java.io.IOException;

public final class ClientWriter {
    private final Client client;
    private final IFileWorker<Toy> toyWriter;

    public ClientWriter(Client client) throws IOException {
        this.client = client;
        this.toyWriter = new JsonFileWorker(client.getName() + ".json");

    }

    public void write() throws IOException {
        toyWriter.write(client.getWonToys());
    }
}
