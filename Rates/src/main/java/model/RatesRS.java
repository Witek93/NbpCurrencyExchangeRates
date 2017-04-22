package model;

import java.util.ArrayList;
import java.util.Collection;

public class RatesRS {
    private Collection<Exchange> exchanges;
    private Result result;

    public Collection<Exchange> getExchanges() {
        if (exchanges == null)
        {
            exchanges = new ArrayList<>();
        }
        return exchanges;
    }

    public RatesRS setExchanges(Collection<Exchange> exchanges) {
        this.exchanges = exchanges;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public RatesRS setResult(Result result) {
        this.result = result;
        return this;
    }
}
