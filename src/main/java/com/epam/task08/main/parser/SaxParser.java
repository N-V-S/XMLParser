package com.epam.task08.main.parser;

import com.epam.task08.main.entity.AbstractPublication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {

    @Override
    public List<AbstractPublication> parse(String fileName) throws ParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        PublicationsHandler handler = new PublicationsHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(fileName, handler);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
        return handler.getPublications();
    }
}