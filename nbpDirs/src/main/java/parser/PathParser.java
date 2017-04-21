package parser;

import model.ExchangeType;
import processors.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PathParser implements Parser<DirectoryData> {
    private static final PathValidator pathValidator = new PathValidator();

    private String directoryPath;
    private DirectoryData directoryData;

    public PathParser(String path) {
        String cleanPath = cleanedUp(path);
        pathValidator.validate(cleanPath);
        this.directoryPath = cleanPath;
    }

    private String cleanedUp(String path) {
        return path.replaceAll("\\P{Print}", "");
    }

    @Override
    public DirectoryData parse() {
        if (directoryData == null) {
            String id = parseId();
            ExchangeType type = parseType();
            LocalDate date = parseDate();
            directoryData = new DirectoryData(id, type, date, directoryPath);
        }

        return directoryData;
    }

    private String parseId() {
        return directoryPath.substring(1, 4);
    }

    private ExchangeType parseType() {
        return ExchangeType.valueOf(directoryPath.substring(0, 1).toUpperCase());
    }

    private LocalDate parseDate() {
        return LocalDate.parse(directoryPath.substring(5), DateTimeFormatter.ofPattern("yyMMdd"));
    }
}
