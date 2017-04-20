package model;

import model.response.Exchange;

import java.util.ArrayList;
import java.util.Collection;

public class QueryRatesResponse {
    private Collection<Exchange> exchanges;

    public Collection<Exchange> getExchanges() {
        if (exchanges == null)
        {
            exchanges = new ArrayList<>();
        }
        return exchanges;
    }

    public QueryRatesResponse setExchanges(Collection<Exchange> exchanges) {
        this.exchanges = exchanges;
        return this;
    }
}
