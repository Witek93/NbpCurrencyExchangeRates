package parser;

import model.ExchangeType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PathParser {
    private static final PathValidator pathValidator = new PathValidator();

    private String directoryPath;

    public PathParser(String directoryPath) {
        pathValidator.validate(directoryPath);
        this.directoryPath = directoryPath;
    }


    public String getDirectoryPath() {
        return directoryPath;
    }

    public String getId() {
        return directoryPath.substring(1, 4);
    }

    public ExchangeType getType() {
        return ExchangeType.valueOf(directoryPath.substring(0, 1).toUpperCase());
    }

    public LocalDate getDate() {
        return LocalDate.parse(directoryPath.substring(5), DateTimeFormatter.ofPattern("yyMMdd"));
    }
}
