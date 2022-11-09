package com.task1.part3.data;

public class Cylinder implements Shape {

    private final double height;
    private final double radius;

    public Cylinder(double height, double radius) {
        if (height <= 0 || radius <= 0)
            throw new IllegalArgumentException("Cylinder height and radius should be more than zero");
        this.height = height;
        this.radius = radius;
    }

    @Override
    public double volume() {
        return Math.PI * radius * radius * height;
    }
}
