package model;

import java.math.BigDecimal;

public class StatisticsRS {
    private StatisticsResult result;
    private BigDecimal standardDeviation;
    private BigDecimal average;

    public StatisticsResult getResult() {
        return result;
    }

    public StatisticsRS setResult(StatisticsResult result) {
        this.result = result;
        return this;
    }

    public BigDecimal getStandardDeviation() {
        return standardDeviation;
    }

    public StatisticsRS setStandardDeviation(BigDecimal standardDeviation) {
        this.standardDeviation = standardDeviation;
        return this;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public StatisticsRS setAverage(BigDecimal average) {
        this.average = average;
        return this;
    }
}
