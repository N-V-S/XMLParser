package com.epam.task08.main.validator;

import org.junit.Assert;
import org.junit.Test;

public class xsdValidatorTest {

    private static final String XSD_FILE = "src/test/resources/publications.xsd";
    private static final String VALID_XML_FILE = "src/test/resources/publications.xml";
    private static final String INVALID_XML_FILE = "src/test/resources/invalid.xml";

    private final xsdValidator validator = new xsdValidator(XSD_FILE);

    @Test
    public void testIsValidShouldReturnTrueWhenFileIsValid() {
        //when
        boolean actual = validator.isValid(VALID_XML_FILE);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenFileIsInvalid() {
        //when
        boolean actual = validator.isValid(INVALID_XML_FILE);
        //then
        Assert.assertFalse(actual);
    }
}