package com.task1.part1;

import org.junit.Assert;
import org.junit.Test;

public class PositiveNumberSorterTests {

    private final PositiveNumbersSorter positiveNumbersSorter = new PositiveNumbersSorter();

    @Test
    public void testSortingWithOnlyPositiveNumbers() {
        Integer[] testArr = new Integer[]{5, 9, 2, 4, 7};
        Integer[] actualResult = positiveNumbersSorter.sortPositiveNumbersInReverseOrder(testArr);
        Integer[] expectedResult = new Integer[]{9, 7, 5, 4, 2};
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testSortingWithNegativeNumbers() {
        Integer[] testArr = new Integer[]{-3, 4, 0, 5, -1};
        Integer[] actualResult = positiveNumbersSorter.sortPositiveNumbersInReverseOrder(testArr);
        Integer[] expectedResult = new Integer[]{5, 4, 0};
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testSortingForEmptyArray() {
        Integer[] testArr = new Integer[]{};
        Integer[] actualResult = positiveNumbersSorter.sortPositiveNumbersInReverseOrder(testArr);
        Integer[] expectedResult = new Integer[]{};
        Assert.assertArrayEquals(expectedResult, actualResult);
    }
}
