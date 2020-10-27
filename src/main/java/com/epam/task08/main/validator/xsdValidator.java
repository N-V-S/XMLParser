package com.epam.task08.main.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class xsdValidator {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    private final String xsdFileName;

    public xsdValidator(String xsdFileName) {
        this.xsdFileName = xsdFileName;
    }

    public boolean isValid(String xmlFileName) {
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        File xsdFile = new File(xsdFileName);
        try {
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFileName);
            validator.validate(source);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}