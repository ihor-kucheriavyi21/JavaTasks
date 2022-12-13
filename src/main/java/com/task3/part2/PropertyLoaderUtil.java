package com.task3.part2;

import com.util.DateTimeUtil;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Properties;

public class PropertyLoaderUtil {

    private PropertyLoaderUtil() {
    }

    @SneakyThrows
    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) {
        Properties propertiesFromFile = new Properties();
        T instance = cls.getDeclaredConstructor().newInstance();

        try (InputStream inputStream = Files.newInputStream(propertiesPath)) {
            propertiesFromFile.load(inputStream);

            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                String fieldValue = readFieldByNameOrIfNotExistByAnnotation(propertiesFromFile, field);
                field.setAccessible(true);
                if (field.getType() == int.class || field.getType() == Integer.class)
                    field.set(instance, Integer.parseInt(fieldValue));
                else if (field.getType() == Instant.class) {

                    Property annotation = field.getAnnotation(Property.class);

                    Instant value = DateTimeUtil.transformInstantFromStringPatternAndValue(annotation.format(),
                            fieldValue);
                    field.set(instance, value);
                } else
                    field.set(instance, fieldValue);

            }
        }
        return instance;
    }

    private static String readFieldByNameOrIfNotExistByAnnotation(Properties properties, Field field) {
        String property = properties.getProperty(field.getName());
        if (property != null) {
            return property;
        } else {

            Property annotation = field.getAnnotation(Property.class);
            if (!annotation.name().equals(""))
                property = properties.getProperty(annotation.name());
            return property;
        }
    }

}
