package com.task1.part3;

import com.task1.part3.data.Cube;
import com.task1.part3.data.Cylinder;
import com.task1.part3.data.Shape;
import com.task1.part3.data.Sphere;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeSorterTest {

    private final ShapeSorter shapeSorter = new ShapeSorter();

    @Test
    public void testSortShapeByVolume() {
        Cube cube = new Cube(10);
        Cylinder cylinder = new Cylinder(10, 4);
        Sphere sphere = new Sphere(5);
        List<Shape> shapes = new ArrayList<>(Arrays.asList(cube, cylinder, sphere));
        List<Shape> actualResult = shapeSorter.sortFigureByVolume(shapes);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResult.get(0))
                .isEqualTo(cylinder);
        softAssertions.assertThat(actualResult.get(1))
                .isEqualTo(sphere);
        softAssertions.assertThat(actualResult.get(2))
                .isEqualTo(cube);
        softAssertions.assertAll();
    }
}
