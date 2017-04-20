package model;

import exceptions.InvalidDataException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class NbpDirectoryResponse {
    private Collection<String> paths;

    public Collection<String> getPaths() {
        if (paths == null)
        {
            paths = new ArrayList<>();
        }
        return paths;
    }

    public NbpDirectoryResponse setPaths(Collection<String> paths) {
        this.paths = paths;
        return this;
    }

    @Override
    public String toString() {
        return "NbpDirectoryResponse{" +
                "paths=" + paths +
                '}';
    }

    public static class NbpDirectoryPathParser {
        private static final String NBP_TYPE = "[AaBbCcHh]";
        private static final String NBP_ID = "\\d{3}";

        private static final String NBP_YEAR = "\\d{2}";
        private static final String NBP_MONTH = "[01]\\d";
        private static final String NBP_DAY = "\\d{2}";
        private static final String NBP_DATE = NBP_YEAR + NBP_MONTH + NBP_DAY;

        private static final String NBP_DIRECTORY_PATH_REGEX = NBP_TYPE + NBP_ID + "[Zz]" + NBP_DATE;

        private String directoryPath;

        public NbpDirectoryPathParser(String directoryPath) {
            validate(directoryPath);
            this.directoryPath = directoryPath;
        }

        private void validate(String directoryPath) {
            if (!directoryPath.matches(NBP_DIRECTORY_PATH_REGEX)) {
                throw new InvalidDataException("Provided argument [" + directoryPath + "] does not match the NBP directory path pattern");
            }
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
}
