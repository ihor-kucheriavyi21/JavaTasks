package com.task1.part3.data;

public class Sphere implements Shape {

    private final double radius;

    public Sphere(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Sphere radius should be more than zero");
        this.radius = radius;
    }

    @Override
    public double volume() {
        return Math.PI * Math.pow(radius, 3) * 4 / 3;
    }
}