package com.task1.part1;

import java.util.Arrays;
import java.util.Comparator;

public class PositiveNumbersSorter {

    public Integer[] sortPositiveNumbersInReverseOrder(Integer[] array) {
        return Arrays.stream(array)
                .filter(element -> element >= 0)
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
    }
}
