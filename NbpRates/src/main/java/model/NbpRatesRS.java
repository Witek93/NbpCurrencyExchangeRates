package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.LocalDate;
import java.util.Collection;

@JacksonXmlRootElement(localName = "tabela_kursow")
public class NbpRatesRS {

    @JacksonXmlProperty(localName = "typ", isAttribute = true)
    private String type;

    @JacksonXmlProperty(localName = "numer_tabeli")
    private String id;

    @JacksonXmlProperty(localName = "data_notowania")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate recordDate;

    @JacksonXmlProperty(localName = "data_publikacji")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate publicationDate;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "pozycja")
    private Collection<NbpRate> rates;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Collection<NbpRate> getRates() {
        return rates;
    }
}
