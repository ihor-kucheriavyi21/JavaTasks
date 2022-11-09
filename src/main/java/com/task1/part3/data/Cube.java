package com.task1.part3.data;

public class Cube implements Shape {

    private final double edge;

    public Cube(double edge) {
        if (edge <= 0)
            throw new IllegalArgumentException("Cube edge should be more than zero");
        this.edge = edge;
    }

    @Override
    public double volume() {
        return Math.pow(edge, 3);
    }
}
