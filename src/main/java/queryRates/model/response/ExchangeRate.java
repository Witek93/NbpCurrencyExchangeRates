package queryRates.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeRate {
    private LocalDate date;
    private BigDecimal buyingRate, sellingRate;

    public LocalDate getDate() {
        return date;
    }

    public ExchangeRate setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BigDecimal getBuyingRate() {
        return buyingRate;
    }

    public ExchangeRate setBuyingRate(BigDecimal buyingRate) {
        this.buyingRate = buyingRate;
        return this;
    }

    public BigDecimal getSellingRate() {
        return sellingRate;
    }

    public ExchangeRate setSellingRate(BigDecimal sellingRate) {
        this.sellingRate = sellingRate;
        return this;
    }

}
