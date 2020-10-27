package com.epam.task08.main.parser;

import com.epam.task08.main.entity.AbstractPublication;
import com.epam.task08.main.entity.Publications;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser{

    @Override
    public List<AbstractPublication> parse(String fileName) throws ParserException {
        Publications publications;
        try {
            JAXBContext context = JAXBContext.newInstance(Publications.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File xmlFile = new File(fileName);
            publications = (Publications) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            throw new ParserException(e.getMessage(), e);
        }
        return publications.getPublications();
    }
}