package com.epam.task08.main.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book")
public class Book extends AbstractPublication {

    @XmlElement(namespace = "http://www.epam.com/publications", required = true)
    private String author;

    public Book() {
    }

    public Book(int id, String title, PublishingHouse publishingHouse, String author) {
        super(id, title, publishingHouse);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Book book = (Book) o;
        return author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }

    @Override
    public String toString() {
        return String.format("Book{author=%s}", author);
    }
}