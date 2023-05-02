package ru.spb.iren.service.writer;

import java.io.IOException;
import java.util.List;

public interface IFileWorker<T> {
  List<T> read() throws IOException;

  void write(List<T> data) throws IOException;
}
