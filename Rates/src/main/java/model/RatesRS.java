package model;

public class RatesRS {
    private Exchange exchange;
    private Result result;

    public Exchange getExchange() {
        return exchange;
    }

    public RatesRS setExchange(Exchange exchange) {
        this.exchange = exchange;
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
