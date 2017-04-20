package model;

import model.Currency;
import model.request.DateRange;

public class QueryRatesRequest {
    private Currency currency;
    private DateRange dateRange;

    public QueryRatesRequest() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public QueryRatesRequest setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public QueryRatesRequest setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
        return this;
    }
}
