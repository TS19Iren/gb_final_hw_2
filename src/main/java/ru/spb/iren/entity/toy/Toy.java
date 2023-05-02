package ru.spb.iren.entity.toy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Абстрактное описание игрушки
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Plush.class, name = "Plush"),
        @JsonSubTypes.Type(value = Robot.class, name = "Robot"),
        @JsonSubTypes.Type(value = SomeRandomToy.class, name = "SomeRandomToy"),
})
public abstract class Toy implements Serializable {
    private final String idVal;
    private String name;
    private int quantity;
    private float probability;


    @JsonCreator
    Toy(@JsonProperty("id") String idVal,
        @JsonProperty("name") String name,
        @JsonProperty("quantity") int quantity,
        @JsonProperty("probability") float probability) {
        this.idVal = idVal;
        this.name = name;
        this.quantity = quantity;
        this.probability = probability;
    }

    public Toy() {
        this.idVal = UUID.randomUUID().toString();
        this.probability = new Random().nextFloat();
        this.quantity = 1;
    }

    public Toy setName(String name) {
        this.name = name;
        return this;
    }

    public Toy setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Toy setProbability(float probability) {
        this.probability = probability;
        return this;
    }

    public String getIdVal() {
        return idVal;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return "Игрушка [имя: %s, кол-во: %s, вероятность: %s]".formatted(name, quantity, probability);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Toy toy)) {
            return false;
        }
        return getQuantity() == toy.getQuantity() && Float.compare(toy.getProbability(), getProbability()) == 0 &&
                getIdVal().equals(toy.getIdVal()) && getName().equals(toy.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVal(), getName(), getQuantity(), getProbability());
    }

    @JsonProperty("type")
    public abstract String getTypeName();

    public Toy copy() {
        String type = this.getTypeName();
        Toy toy = new Toy() {
            @Override
            public String getTypeName() {
                return type;
            }
        };
        toy.setName(this.name).setProbability(this.probability).setQuantity(this.quantity);
        return toy;
    }
}
