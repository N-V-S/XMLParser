package com.epam.task08.main.parser;

public class ParserException extends Exception {

    public ParserException(String message, Exception e) {
        super(message, e);
    }
}