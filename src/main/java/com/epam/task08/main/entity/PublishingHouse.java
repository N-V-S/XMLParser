package com.epam.task08.main.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "publishingHouse")
public final class PublishingHouse {

    @XmlElement(namespace = "http://www.epam.com/publications", required = true)
    private String name;
    @XmlElement(name = "establishment-year", namespace = "http://www.epam.com/publications", required = true)
    private int establishmentYear;

    public PublishingHouse() {
    }

    public PublishingHouse(String name, int establishmentYear) {
        this.name = name;
        this.establishmentYear = establishmentYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(int establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PublishingHouse that = (PublishingHouse) o;
        return establishmentYear == that.establishmentYear &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, establishmentYear);
    }

    @Override
    public String toString() {
        return String.format("PublishingHouse{name=%s, establishmentYear=%s}", name, establishmentYear);
    }
}