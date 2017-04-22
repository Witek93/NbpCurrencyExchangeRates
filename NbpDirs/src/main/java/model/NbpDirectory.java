package model;

import java.time.LocalDate;

public class NbpDirectory {
    private ExchangeType type;
    private String id;
    private LocalDate date;
    private String fileName;

    public ExchangeType getType() {
        return type;
    }

    public NbpDirectory setType(ExchangeType type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public NbpDirectory setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public NbpDirectory setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public NbpDirectory setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public String toString() {
        return "NbpDirectory{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", date=" + date +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
