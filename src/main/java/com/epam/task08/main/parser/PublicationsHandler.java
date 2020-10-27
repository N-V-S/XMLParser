package com.epam.task08.main.parser;

import com.epam.task08.main.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PublicationsHandler extends DefaultHandler {

    private static final String PUBLICATIONS = "publications";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PUBLISHING_HOUSE = "publishing-house";
    private static final String NAME = "name";
    private static final String ESTABLISHMENT_YEAR = "establishment-year";
    private static final String BOOK = "book";
    private static final String AUTHOR = "author";
    private static final String PERIODICAL = "periodical";
    private static final String PERIODICAL_TYPE = "type";
    private static final String PERIODICITY = "periodicity";

    private final List<AbstractPublication> publications = new ArrayList<AbstractPublication>();
    private AbstractPublication publication;
    private PublishingHouse publishingHouse;
    private String currentElement;

    public List<AbstractPublication> getPublications() {
        return publications;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case BOOK:
                publication = new Book();
                setPublicationId(attributes);
                break;
            case PERIODICAL:
                publication = new Periodical();
                setPublicationId(attributes);
                break;
            case PUBLICATIONS:
                break;
            default:
                currentElement = qName;
                break;
        }
    }

    private void setPublicationId(Attributes attributes) {
        String idValue = attributes.getValue(ID);
        int id = Integer.parseInt(idValue);
        publication.setId(id);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case PUBLISHING_HOUSE:
                publication.setPublishingHouse(publishingHouse);
                publishingHouse = null;
                break;
            case BOOK:
            case PERIODICAL:
                publications.add(publication);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (currentElement == null) {
            return;
        }
        String value = new String(ch, start, length);
        Periodical periodical;
        switch (currentElement) {
            case TITLE:
                publication.setTitle(value);
                break;
            case AUTHOR:
                Book book = (Book) publication;
                book.setAuthor(value);
                break;
            case PERIODICAL_TYPE:
                periodical = (Periodical) publication;
                PeriodicalType type = PeriodicalType.valueOf(value);
                periodical.setType(type);
                break;
            case PERIODICITY:
                periodical = (Periodical) publication;
                Periodicity periodicity = Periodicity.valueOf(value);
                periodical.setPeriodicity(periodicity);
                break;
            case PUBLISHING_HOUSE:
                publishingHouse = new PublishingHouse();
                break;
            case NAME:
                publishingHouse.setName(value);
                break;
            case ESTABLISHMENT_YEAR:
                int establishmentYear = Integer.parseInt(value);
                publishingHouse.setEstablishmentYear(establishmentYear);
                break;
            default:
                throw new IllegalArgumentException("Unexpected element: " + currentElement);
        }
        currentElement = null;
    }
}