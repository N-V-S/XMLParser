package com.epam.task08.main.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "publications", namespace = "http://www.epam.com/publications")
@XmlAccessorType(XmlAccessType.FIELD)
public class Publications {
    @XmlElements({
            @XmlElement(name = "book", namespace = "http://www.epam.com/publications", type = Book.class),
            @XmlElement(name = "periodical", namespace = "http://www.epam.com/publications", type = Periodical.class)
    })
    private List<AbstractPublication> publications;

    public List<AbstractPublication> getPublications()
    {
        if (publications == null) {
            publications = new ArrayList<AbstractPublication>();
        }
        return this.publications;
    }
}