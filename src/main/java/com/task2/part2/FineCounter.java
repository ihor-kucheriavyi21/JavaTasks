package com.task2.part2;

import com.task2.part2.data.Fine;

import java.util.*;
import java.util.stream.Collectors;

public class FineCounter {

    public Map<String, Double> sortAndCountFineAmount(List<Fine> fineList) {
        Map<String, Double> mapElements = fineList.stream()
                .collect(Collectors.groupingBy(
                        Fine::getType,
                        Collectors.summingDouble(Fine::getAmount)));
        return mapElements.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
