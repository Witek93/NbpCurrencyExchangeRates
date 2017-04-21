package parser;

import com.sun.istack.internal.NotNull;
import model.Currency;

import java.time.LocalDate;

public class InputData {
    private Currency currency;
    private LocalDate startDate;
    private LocalDate endDate;

    public InputData(@NotNull Currency currency, @NotNull LocalDate startDate, @NotNull LocalDate endDate) {
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
