package model;

public class RatesRS {
    private Exchange exchange;
    private RatesResult result;

    public Exchange getExchange() {
        return exchange;
    }

    public RatesRS setExchange(Exchange exchange) {
        this.exchange = exchange;
        return this;
    }

    public RatesResult getResult() {
        return result;
    }

    public RatesRS setResult(RatesResult result) {
        this.result = result;
        return this;
    }
}
