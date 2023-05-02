package ru.spb.iren.entity.toy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Plush")
public class Plush extends Toy {
  public Plush() {
    this.setName("Плюшевая игрушка");
  }
  @Override
  @JsonProperty("type")
  public String getTypeName() {
    return this.getClass().getSimpleName();
  }
}
