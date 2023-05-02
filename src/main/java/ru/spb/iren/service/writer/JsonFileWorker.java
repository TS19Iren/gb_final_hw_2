package ru.spb.iren.service.writer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import ru.spb.iren.entity.toy.Toy;

public class JsonFileWorker implements IFileWorker<Toy> {
  private final Path filePath;
  private final ObjectMapper objectMapper;

  public JsonFileWorker(String fileName) throws IOException {
    this.filePath = Path.of(fileName);
    if (!Files.exists(filePath)) {
      Files.createFile(filePath);
      Files.writeString(filePath, List.of().toString());
    }
    objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  @Override
  public List<Toy> read() throws IOException {
    final var s = Files.readString(filePath);
    return objectMapper.readValue(s, new TypeReference<>() {
    });
  }

  @Override
  public void write(List<Toy> data) throws IOException {
    Files.writeString(filePath, objectMapper.writeValueAsString(data));
  }
}
