package com.task1.part2;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HashTagFinderTests {

    private final HashTagFinder hashTagFinder = new HashTagFinder();

    @Test
    public void testHashTagFinder() {
        String top1Element = "#first";
        int expectedResultForFirstElement = 12;
        String top2Element = "#qwe";
        int expectedResultForSecondElement = 10;
        String top3Element = "#start";
        int expectedResultForThirdElement = 9;
        String top4Element = "#hard";
        int expectedResultForFourthElement = 9;
        String top5Element = "#top";
        int expectedResultForFifthElement = 6;
        String sixthElement = "#second";

        List<String> stringList = new ArrayList<>(Arrays.asList(top5Element, top5Element, "top " + top5Element, top5Element, "top", top5Element, top5Element,
                top2Element + " qwe", top2Element, "qwe " + top2Element, top2Element, top2Element, top2Element, top2Element, top2Element, top2Element, top2Element,
                top3Element, top3Element + " " + top4Element, top3Element, top3Element, top3Element, top3Element, top3Element, top3Element, top3Element,
                top4Element, top4Element, top4Element, top4Element, top4Element, top4Element, top4Element, top4Element,
                top1Element + " " + sixthElement, top1Element + " " + sixthElement, top1Element, top1Element,
                top1Element, top1Element, top1Element, top1Element, top1Element, top1Element + " " + sixthElement, top1Element + " " + sixthElement, top1Element + " " + sixthElement));
        Map<String, Integer> top5HashTagFromList = hashTagFinder.findTop5HashTagFromList(stringList);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(top5HashTagFromList.get(top1Element))
                .isEqualTo(expectedResultForFirstElement);
        softAssertions.assertThat(top5HashTagFromList.get(top2Element))
                .isEqualTo(expectedResultForSecondElement);
        softAssertions.assertThat(top5HashTagFromList.get(top3Element))
                .isEqualTo(expectedResultForThirdElement);
        softAssertions.assertThat(top5HashTagFromList.get(top4Element))
                .isEqualTo(expectedResultForFourthElement);
        softAssertions.assertThat(top5HashTagFromList.get(top5Element))
                .isEqualTo(expectedResultForFifthElement);
        softAssertions.assertThat(top5HashTagFromList.containsKey(sixthElement))
                .isFalse();
        softAssertions.assertAll();
    }

}
