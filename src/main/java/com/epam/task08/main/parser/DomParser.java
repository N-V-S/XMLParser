package com.epam.task08.main.parser;

import com.epam.task08.main.entity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String NAME = "name";
    private static final String ESTABLISHMENT_YEAR = "establishment-year";
    private static final String BOOK = "book";
    private static final String AUTHOR = "author";
    private static final String PERIODICAL = "periodical";
    private static final String PERIODICAL_TYPE = "type";
    private static final String PERIODICITY = "periodicity";

    @Override
    public List<AbstractPublication> parse(String fileName) throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(fileName);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
        return buildListPublications(document);
    }

    private List<AbstractPublication> buildListPublications(Document document) {
        List<AbstractPublication> publications = new ArrayList<AbstractPublication>();

        Element rootElement = document.getDocumentElement();
        NodeList publicationsNodeList = rootElement.getChildNodes();

        for (int i = 0; i < publicationsNodeList.getLength(); i++) {
            Node node = publicationsNodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element publicationElement = (Element) node;
                AbstractPublication publication = buildPublication(publicationElement);
                publications.add(publication);
            }
        }
        return publications;
    }

    private AbstractPublication buildPublication(Element publicationElement) {
        AbstractPublication publication;
        switch (publicationElement.getTagName()) {
            case BOOK:
                publication = buildBook(publicationElement);
                break;
            case PERIODICAL:
                publication = buildPeriodical(publicationElement);
                break;
            default:
                throw new IllegalArgumentException("Unexpected tag: " + publicationElement.getTagName());
        }

        String idString = publicationElement.getAttribute(ID);
        int id = Integer.parseInt(idString);
        publication.setId(id);

        String title = getElementTextContent(publicationElement, TITLE);
        publication.setTitle(title);

        PublishingHouse publishingHouse = buildPublishingHouse(publicationElement);
        publication.setPublishingHouse(publishingHouse);

        return publication;
    }

    private Book buildBook(Element bookElement) {
        Book book = new Book();

        String author = getElementTextContent(bookElement, AUTHOR);
        book.setAuthor(author);

        return book;
    }

    private Periodical buildPeriodical(Element periodicalElement) {
        Periodical periodical = new Periodical();

        String periodicalType = getElementTextContent(periodicalElement, PERIODICAL_TYPE);
        PeriodicalType type = PeriodicalType.valueOf(periodicalType);
        periodical.setType(type);

        String periodicityString = getElementTextContent(periodicalElement, PERIODICITY);
        Periodicity periodicity = Periodicity.valueOf(periodicityString);
        periodical.setPeriodicity(periodicity);

        return periodical;
    }

    private PublishingHouse buildPublishingHouse(Element publicationElement) {
        PublishingHouse publishingHouse = new PublishingHouse();

        String name = getElementTextContent(publicationElement, NAME);
        publishingHouse.setName(name);

        String establishmentYearString = getElementTextContent(publicationElement, ESTABLISHMENT_YEAR);
        int establishmentYear = Integer.parseInt(establishmentYearString);
        publishingHouse.setEstablishmentYear(establishmentYear);

        return publishingHouse;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}