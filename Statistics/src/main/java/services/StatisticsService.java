package services;

import model.DateRange;
import model.ExchangeRate;
import model.RatesRQ;
import model.RatesRS;
import model.StatisticsRS;
import parser.InputData;
import parser.InputDataParser;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_DOWN;
import static java.util.stream.Collectors.toList;
import static model.StatisticsResult.SUCCESS;

public class StatisticsService {
    private RatesService ratesService = new RatesService();

    public StatisticsRS call(String... args) {
        RatesRS ratesRS = callRatesService(args);

        BigDecimal average = calculateAverage(ratesRS);
        BigDecimal standardDeviation = calculateStandardDeviation(ratesRS);

        return new StatisticsRS()
                .setAverage(average)
                .setStandardDeviation(standardDeviation)
                .setResult(SUCCESS);
    }

    private BigDecimal calculateStandardDeviation(RatesRS ratesRS) {
        List<BigDecimal> sellingRates = ratesRS.getExchange()
                .getExchangeRates()
                .stream()
                .map(ExchangeRate::getSellingRate)
                .collect(toList());

        BigDecimal average = calculateAverage(sellingRates);

        BigDecimal variance = sellingRates.stream()
                .map(rate -> rate.subtract(average).pow(2))
                .reduce(ZERO, BigDecimal::add)
                .divide(valueOf(sellingRates.size()), HALF_DOWN);

        return valueOf(Math.sqrt(variance.doubleValue()));
    }

    private BigDecimal calculateAverage(RatesRS ratesRS) {
        List<BigDecimal> buyingRates = ratesRS.getExchange()
                .getExchangeRates()
                .stream()
                .map(ExchangeRate::getBuyingRate)
                .collect(toList());

        return calculateAverage(buyingRates);
    }

    private BigDecimal calculateAverage(List<BigDecimal> buyingRates) {
        BigDecimal sumOfExchangeRates = buyingRates.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sumOfExchangeRates.divide(valueOf(buyingRates.size()), HALF_DOWN);
    }

    private RatesRS callRatesService(String[] args) {
        InputDataParser parser = new InputDataParser(args);
        InputData inputData = parser.parse();

        RatesRQ ratesRQ = new RatesRQ()
                .setCurrency(inputData.getCurrency())
                .setDateRange(new DateRange(inputData.getStartDate(), inputData.getEndDate()));

        return ratesService.call(ratesRQ);
    }
}
