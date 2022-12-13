package com.task3.part2;

import com.util.DateTimeUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.nio.file.Path;

public class PropertyLoaderUtilTest {

    Path path = Path.of("src/test/resources/task3/part2/test.properties");

    @Test
    public void testLoadDataFromPropertyFile() {
        Entity entity = PropertyLoaderUtil.loadFromProperties(Entity.class, path);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(entity.getMyNumber())
                .isEqualTo(10);
        softAssertions.assertThat(entity.getStringProperty())
                .isEqualTo("value1");
        softAssertions.assertThat(entity.getTimeProperty())
                .isEqualTo(DateTimeUtil.transformInstantFromStringPatternAndValue("dd.MM.yyyy HH:mm",
                        "29.11.2022 18:30"));
        softAssertions.assertAll();
    }
}
