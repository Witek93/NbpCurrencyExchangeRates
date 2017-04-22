package parser;

import model.ExchangeType;

import java.time.LocalDate;

public class DirectoryData {
    private String id;
    private ExchangeType type;
    private LocalDate date;
    private String directoryPath;

    public DirectoryData(String id, ExchangeType type, LocalDate date, String directoryPath) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.directoryPath = directoryPath;
    }

    public String getId() {
        return id;
    }

    public ExchangeType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }
}
