package com.task2.part1;

import com.task2.part1.data.Persons;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersonParserTest {

    private static final String RESULT_FILE_PATH = "src/test/resources/task2/part1/result.xml";
    private static final String INPUT_DATA_PATH = "src/test/resources/task2/part1/persons.xml";

    @Test
    public void testPersonParser() {
        Persons persons = getPersonsFromXmlFile();
        Assert.assertEquals("Count persons from xml file should be 4",
                4, persons.getPersonList().size());
    }

    @SneakyThrows
    @Test
    public void testSaveToXml() {
        Persons persons = getPersonsFromXmlFile();
        JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(persons, Files.newOutputStream(Paths.get(RESULT_FILE_PATH)));
        File file = new File(RESULT_FILE_PATH);
        Assert.assertTrue("File shouldn't be empty", file.length() > 0);
    }

    @SneakyThrows
    private Persons getPersonsFromXmlFile() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PersonHandler personHandler = new PersonHandler();
        saxParser.parse(INPUT_DATA_PATH, personHandler);
        return personHandler.getRootElement();
    }
}
