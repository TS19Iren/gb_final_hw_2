package ru.spb.iren.entity.toy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Robot")
public class Robot extends Toy {
    public Robot() {
        this.setQuantity(1);
        this.setName("Robot");

    }

    @Override
    @JsonProperty("type")
    public String getTypeName() {
        return this.getClass().getSimpleName();
    }
}
