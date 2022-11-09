package com.task1.part2;

import java.util.*;
import java.util.stream.Collectors;

public class HashTagFinder {

    public Map<String, Integer> findTop5HashTagFromList(List<String> hashTags) {
        Map<String, Integer> repeatedHashTagCount = new HashMap<>();
        for (String string : hashTags) {
            Set<String> setUniqueWords = new HashSet<>(Arrays.asList(string.split(" ")));
            setUniqueWords.stream()
                    .filter(word -> word.startsWith("#"))
                    .forEach(word -> {
                        if (repeatedHashTagCount.containsKey(word))
                            repeatedHashTagCount.put(word, repeatedHashTagCount.get(word) + 1);
                        else
                            repeatedHashTagCount.put(word, 1);
                    });
        }
        return repeatedHashTagCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
