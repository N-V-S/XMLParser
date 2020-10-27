package com.epam.task08.main.entity;

import com.epam.task08.main.parser.DomParser;
import com.epam.task08.main.parser.JaxbParser;
import com.epam.task08.main.parser.Parser;
import com.epam.task08.main.parser.SaxParser;

public class ParserFactory {

    public Parser createParser(ParserType type) {
        switch (type) {
            case DOM:
                return new DomParser();
            case SAX:
                return new SaxParser();
            case JAXB:
                return new JaxbParser();
            default:
                throw new IllegalArgumentException("Unknown parser type = " + type);
        }
    }
}