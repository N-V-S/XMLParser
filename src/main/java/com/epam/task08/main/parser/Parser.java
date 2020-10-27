package com.epam.task08.main.parser;

import com.epam.task08.main.entity.AbstractPublication;
import java.util.List;

public interface Parser {

    List<AbstractPublication> parse(String fileName) throws ParserException;
}