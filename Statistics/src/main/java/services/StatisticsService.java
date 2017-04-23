package services;

import model.DateRange;
import model.ExchangeRate;
import model.RatesRQ;
import model.RatesRS;
import model.RatesResult;
import model.StatisticsRS;
import model.StatisticsResult;
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
        StatisticsRS statisticsRS = new StatisticsRS()
                .setResult(SUCCESS);

        RatesRS ratesRS = callRatesService(args);

        if (ratesRS.getResult() == RatesResult.SUCCESS) {
            statisticsRS
                    .setAverage(calculateAverage(ratesRS))
                    .setStandardDeviation(calculateStandardDeviation(ratesRS));
        } else {
            statisticsRS.setResult(mapRatesResult(ratesRS.getResult()));
        }

        return statisticsRS;
    }

    private StatisticsResult mapRatesResult(RatesResult result) {
        switch (result) {
            case RATES_NOT_FOUND_FOR_GIVEN_CURRENCY:
                return StatisticsResult.RATES_SERVICE_INVALID_CURRENCY;
            default:
                return StatisticsResult.RATES_SERVICE_FAILED;
        }
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
