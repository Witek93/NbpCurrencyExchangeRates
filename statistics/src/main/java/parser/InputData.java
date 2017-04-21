package parser;

import model.Currency;

import java.time.LocalDate;

public class InputData {
    private Currency currency;
    private LocalDate startDate;
    private LocalDate endDate;

    public InputData(Currency currency, LocalDate startDate, LocalDate endDate) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
