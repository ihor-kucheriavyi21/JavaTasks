package com.task2.part1;

import com.task2.part1.data.Person;
import com.task2.part1.data.Persons;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class PersonHandler extends DefaultHandler {
    private static final String PERSONS = "persons";
    private static final String PERSON = "person";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String BIRTHDATE = "birthDate";
    private static final String BIRTH_DATA = "birthData";

    private Persons rootElement;
    private StringBuilder elementValue;

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() {
        rootElement = new Persons();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        if (PERSONS.equals(qName)) {
            rootElement.setPersonList(new ArrayList<>());
        } else if (PERSON.equals(qName)) {
            rootElement.getPersonList().add(new Person(attr.getValue(NAME).concat(" ").concat(attr.getValue(SURNAME)),
                    attr.getValue(BIRTHDATE) != null ? attr.getValue(BIRTHDATE) : attr.getValue(BIRTH_DATA)));
        }
    }

    public Persons getRootElement() {
        return rootElement;
    }
}
