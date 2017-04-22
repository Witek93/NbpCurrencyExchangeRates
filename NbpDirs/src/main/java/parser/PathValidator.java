package parser;

import exceptions.InvalidDataException;

public class PathValidator {
    private static final String NBP_TYPE = "[AaBbCcHh]";
    private static final String NBP_ID = "\\d{3}";

    private static final String NBP_YEAR = "\\d{2}";
    private static final String NBP_MONTH = "[01]\\d";
    private static final String NBP_DAY = "\\d{2}";
    private static final String NBP_DATE = NBP_YEAR + NBP_MONTH + NBP_DAY;

    private static final String NBP_DIRECTORY_PATH_REGEX = NBP_TYPE + NBP_ID + "[Zz]" + NBP_DATE;


    public void validate(String directoryPath) {
        if (!directoryPath.matches(NBP_DIRECTORY_PATH_REGEX)) {
            throw new InvalidDataException("Provided argument [" + directoryPath + "] does not match the NBP directory path pattern");
        }
    }
}
