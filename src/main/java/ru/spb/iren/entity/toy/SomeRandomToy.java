package ru.spb.iren.entity.toy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("SomeRandomToy")

public class SomeRandomToy extends Toy {
    @JsonCreator
    private SomeRandomToy(@JsonProperty("id") String idVal,
                          @JsonProperty("name") String name,
                          @JsonProperty("quantity") int quantity,
                          @JsonProperty("probability") float probability) {
        super(idVal, name, quantity, probability);
    }

    public SomeRandomToy(String name) {
        this.setName(name);
    }

    @Override
    @JsonProperty("type")
    public String getTypeName() {
        return this.getClass().getSimpleName();
    }
}
