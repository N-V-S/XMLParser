package com.epam.task08.main.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractPublication")
@XmlSeeAlso({
        Book.class,
        Periodical.class
})
public abstract class AbstractPublication {

    @XmlAttribute(name = "id", required = true)
    private int id;
    @XmlElement(namespace = "http://www.epam.com/publications", required = true)
    private String title;
    @XmlElement(name = "publishing-house", namespace = "http://www.epam.com/publications", required = true)
    private PublishingHouse publishingHouse;

    public AbstractPublication() {
    }

    public AbstractPublication(int id, String title, PublishingHouse publishingHouse) {
        this.id = id;
        this.title = title;
        this.publishingHouse = publishingHouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPublication that = (AbstractPublication) o;
        return id == that.id &&
                title.equals(that.title) &&
                publishingHouse.equals(that.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publishingHouse);
    }

    @Override
    public String toString() {
        return String.format("Publication{title=%s, publishingHouse=%s", title, publishingHouse);
    }
}