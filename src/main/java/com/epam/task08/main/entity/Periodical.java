package com.epam.task08.main.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Periodical")
public class Periodical extends AbstractPublication {

    @XmlElement(namespace = "http://www.epam.com/publications", required = true)
    private PeriodicalType type;
    @XmlElement(namespace = "http://www.epam.com/publications", required = true)
    private Periodicity periodicity;

    public Periodical() {
    }

    public Periodical(int id, String title, PublishingHouse publishingHouse, PeriodicalType type, Periodicity periodicity) {
        super(id, title, publishingHouse);
        this.type = type;
        this.periodicity = periodicity;
    }

    public PeriodicalType getType() {
        return type;
    }

    public void setType(PeriodicalType type) {
        this.type = type;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
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
        Periodical that = (Periodical) o;
        return type == that.type &&
                periodicity == that.periodicity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, periodicity);
    }

    @Override
    public String toString() {
        return String.format("Periodical{periodicity=%s}", periodicity);
    }
}