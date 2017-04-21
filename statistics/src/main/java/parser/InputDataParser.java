package parser;

import exceptions.InvalidDataException;
import model.Currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class InputDataParser {
    private static final int CURRENCY_CODE_LENGTH = 3;

    private String[] inputData;

    private Currency currency;
    private LocalDate startDate;
    private LocalDate endDate;

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public InputDataParser(String... inputData) {
        if (inputData == null || inputData.length != 3) {
            throw new IllegalArgumentException("Parser accepts only exactly 3 arguments.");
        }
        this.inputData = inputData;
    }

    public void parse() {
        currency = parseCurrency();
        startDate = parseStartDate();
        endDate = parseEndDate();
    }

    private Currency parseCurrency() {
        return Optional.ofNullable(inputData[0])
                .filter(currencyCode -> currencyCode.length() >= CURRENCY_CODE_LENGTH)
                .filter(currencyCode -> currencyCode.matches("[A-Za-z]*"))
                .map(Currency::new)
                .orElseThrow(() -> new InvalidDataException("Parser failed to parse currency code"));
    }

    private LocalDate parseStartDate() {
        try {
            return parseDate(inputData[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Parser failed to parse start date");
        }
    }

    private LocalDate parseEndDate() {
        try {
            return parseDate(inputData[2]);
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Parser failed to parse start date");
        }

    }

    private LocalDate parseDate(String maybeLocalDate) {
        return LocalDate.parse(maybeLocalDate, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
