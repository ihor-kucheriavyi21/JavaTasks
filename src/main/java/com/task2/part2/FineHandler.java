package com.task2.part2;

import com.task2.part2.data.Fine;
import com.task2.part2.data.Fines;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class FineHandler extends DefaultHandler {

    private static final String FINES = "fines";
    private static final String FINE = "fine";
    private static final String TYPE = "type";
    private static final String FINE_AMOUNT = "fine_amount";
    private static final String DATE_TIME = "date_time";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    private Fines rootElement;
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
        rootElement = new Fines();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case FINES:
                rootElement.setFineList(new ArrayList<>());
                break;
            case FINE:
                rootElement.getFineList().add(new Fine());
                break;
            /*case LAST_NAME:
                latestFine().setLastName(elementValue.toString());
                break;
            case FIRST_NAME:
                latestFine().setFirstName(elementValue.toString());
                break;
            case DATE_TIME:
                latestFine().setDateTime(elementValue.toString());
                break;*/
            case TYPE:
            case FINE_AMOUNT:
                elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
           /* case LAST_NAME:
                latestFine().setLastName(elementValue.toString());
                break;
            case FIRST_NAME:
                latestFine().setFirstName(elementValue.toString());
                break;
            case DATE_TIME:
                latestFine().setDateTime(elementValue.toString());
                break;*/
            case TYPE:
                latestFine().setType(elementValue.toString());
                break;
            case FINE_AMOUNT:
                latestFine().setAmount(Double.parseDouble(elementValue.toString()));
                break;
        }
    }

    private Fine latestFine() {
        List<Fine> fineList = rootElement.getFineList();
        int latestIndex = fineList.size() - 1;
        return fineList.get(latestIndex);
    }

    public Fines getRootElement() {
        return rootElement;
    }
}
