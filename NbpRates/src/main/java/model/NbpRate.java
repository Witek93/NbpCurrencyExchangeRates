package model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class NbpRate {
    @JacksonXmlProperty(localName = "nazwa_waluty")
    private String currencyName;

    @JacksonXmlProperty(localName = "przelicznik")
    private Integer scale;

    @JacksonXmlProperty(localName = "kod_waluty")
    private String currencyCode;

    @JacksonXmlProperty(localName = "kurs_kupna")
    @JsonDeserialize(using = NbpDoubleDeserializer.class)
    private Double buyingRate;

    @JacksonXmlProperty(localName = "kurs_sprzedazy")
    @JsonDeserialize(using = NbpDoubleDeserializer.class)
    private Double sellingRate;

    public String getCurrencyName() {
        return currencyName;
    }

    public Integer getScale() {
        return scale;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Double getBuyingRate() {
        return buyingRate;
    }

    public Double getSellingRate() {
        return sellingRate;
    }
}
