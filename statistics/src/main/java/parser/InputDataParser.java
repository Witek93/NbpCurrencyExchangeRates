package parser;

import exceptions.InvalidDataException;
import model.Currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class InputDataParser {
    private static final int CURRENCY_CODE_LENGTH = 3;

    private String[] rawInputData;
    private InputData parsedData;

    public InputDataParser(String... rawInputData) {
        if (rawInputData == null || rawInputData.length != 3) {
            throw new IllegalArgumentException("Parser accepts only exactly 3 arguments.");
        }
        this.rawInputData = rawInputData;
    }

    public InputData parse() {
        if (parsedData == null) {
            Currency currency = parseCurrency();
            LocalDate startDate = parseStartDate();
            LocalDate endDate = parseEndDate();
            parsedData = new InputData(currency, startDate, endDate);
        }

        return parsedData;
    }

    private Currency parseCurrency() {
        return Optional.ofNullable(rawInputData[0])
                .filter(currencyCode -> currencyCode.length() >= CURRENCY_CODE_LENGTH)
                .filter(currencyCode -> currencyCode.matches("[A-Za-z]*"))
                .map(Currency::new)
                .orElseThrow(() -> new InvalidDataException("Parser failed to parse currency code"));
    }

    private LocalDate parseStartDate() {
        try {
            return parseDate(rawInputData[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Parser failed to parse start date");
        }
    }

    private LocalDate parseEndDate() {
        try {
            return parseDate(rawInputData[2]);
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Parser failed to parse start date");
        }

    }

    private LocalDate parseDate(String maybeLocalDate) {
        return LocalDate.parse(maybeLocalDate, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
