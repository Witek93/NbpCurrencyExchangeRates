package model;

public class RatesRQ {
    private Currency currency;
    private DateRange dateRange;

    public RatesRQ() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public RatesRQ setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public RatesRQ setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
        return this;
    }
}
