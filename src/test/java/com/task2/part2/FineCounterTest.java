package com.task2.part2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task2.part1.PersonHandler;
import com.task2.part1.data.Persons;
import com.task2.part2.data.Fine;
import com.task2.part2.data.Fines;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FineCounterTest {

    private static final String RESULT_FILE_PATH = "src/test/resources/task2/part2/output/result.json";
    private static final String INPUT_DIRECTORY_PATH = "src/test/resources/task2/part2/input";


    @SneakyThrows
    @Test
    public void testCountFinesFromDirectory() {
        File folder = new File(INPUT_DIRECTORY_PATH);
        File[] listOfFiles = folder.listFiles();
        List<Fine> fineList = new ArrayList<>();
        int length = Objects.requireNonNull(listOfFiles).length;
        for (int i = 0; i < length; i++) {
            if (listOfFiles[i].isFile()) {
                fineList.addAll(getFinesFromXmlFile(listOfFiles[i]).getFineList());
            }
        }
        Map<String, Double> result = new FineCounter().sortAndCountFineAmount(fineList);
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(RESULT_FILE_PATH), result);
        File file = new File(RESULT_FILE_PATH);
        Assert.assertTrue("File shouldn't be empty", file.length() > 0);

    }

    @SneakyThrows
    private Fines getFinesFromXmlFile(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        FineHandler fineHandler = new FineHandler();
        saxParser.parse(file.getPath(), fineHandler);
        return fineHandler.getRootElement();
    }
}
