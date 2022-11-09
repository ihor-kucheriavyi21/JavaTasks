package com.task1.part3;

import com.task1.part3.data.Shape;

import java.util.Comparator;
import java.util.List;

public class ShapeSorter {

    public List<Shape> sortFigureByVolume(List<Shape> shapes) {
        shapes.sort(Comparator.comparingDouble(Shape::volume));
        return shapes;
    }
}
