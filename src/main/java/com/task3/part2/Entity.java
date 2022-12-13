package com.task3.part2;

import lombok.Data;

import java.time.Instant;

@Data
public class Entity {
    private String stringProperty;

    @Property(name="numberProperty")
    private int myNumber;

    @Property(format="dd.MM.yyyy HH:mm")
    private Instant timeProperty;
}
