package queryRates.model.response;

import queryRates.model.common.Currency;

import java.util.ArrayList;
import java.util.Collection;

public class Exchange {
    private Currency currency;
    private Collection<ExchangeRate> exchangeRates;

    public Currency getCurrency() {
        return currency;
    }

    public Exchange setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public Collection<ExchangeRate> getExchangeRates() {
        if (exchangeRates == null)
        {
            exchangeRates = new ArrayList<>();
        }
        return exchangeRates;
    }

    public Exchange setExchangeRates(Collection<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
        return this;
    }

}
