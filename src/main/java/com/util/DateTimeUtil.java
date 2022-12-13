package com.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static Instant transformInstantFromStringPatternAndValue(String pattern, String value) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(value, dateTimeFormatter);
        return localDateTime.toInstant(ZoneOffset.UTC);
    }
}
