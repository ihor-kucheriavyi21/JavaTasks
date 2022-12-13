package com.task2.part2;

import com.task2.part2.data.Fine;
import com.task2.part2.data.Fines;
import lombok.SneakyThrows;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FineHandler extends DefaultHandler implements IFineHandler {

    private static final String FINES = "fines";
    private static final String FINE = "fine";
    private static final String TYPE = "type";
    private static final String FINE_AMOUNT = "fine_amount";

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
            case TYPE:
            case FINE_AMOUNT:
                elementValue = new StringBuilder();
                break;
            default:
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (TYPE.equals(qName)) {
            latestFine().setType(elementValue.toString());
        } else if (FINE_AMOUNT.equals(qName)) {
            latestFine().setAmount(Double.parseDouble(elementValue.toString()));
        }
    }

    private Fine latestFine() {
        List<Fine> fineList = rootElement.getFineList();
        int latestIndex = fineList.size() - 1;
        return fineList.get(latestIndex);
    }

    @SneakyThrows
    public Fines getFinesFromXmlFile(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        SAXParser saxParser = factory.newSAXParser();
        FineHandler fineHandler = new FineHandler();
        saxParser.parse(file.getPath(), fineHandler);
        return fineHandler.getRootElement();
    }

    private Fines getRootElement() {
        return rootElement;
    }
}
