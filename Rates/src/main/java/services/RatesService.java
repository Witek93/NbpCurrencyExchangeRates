package services;

import extensions.DirectoriesDecorator;
import model.Currency;
import model.Directory;
import model.Exchange;
import model.ExchangeRate;
import model.GetDirectoriesRS;
import model.NbpRate;
import model.NbpRatesRS;
import model.RatesRQ;
import model.RatesRS;
import model.RatesResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static model.RatesResult.FAILURE;
import static model.RatesResult.RATES_NOT_FOUND_FOR_GIVEN_CURRENCY;
import static model.RatesResult.SUCCESS;

public class RatesService {

    private NbpRatesService nbpRatesService = new NbpRatesService();
    private GetDirectoriesService getDirectoriesService = new GetDirectoriesService();

    public RatesRS call(RatesRQ ratesRQ) {

        RatesRS response = new RatesRS();
        RatesResult result = SUCCESS;

        if (ratesRQ.getCurrency() == null) {
            return response.setResult(FAILURE);
        }

        Collection<Year> years = ratesRQ.getDateRange().getYears();
        GetDirectoriesRS getDirectoriesRS = getDirectoriesService.call(years);

        DirectoriesDecorator decoratedDirectories = new DirectoriesDecorator(getDirectoriesRS);

        List<Directory> validDirectories = ratesRQ.getDateRange().getDays()
                .stream()
                .flatMap(decoratedDirectories::findForDate)
                .collect(toList());

        Currency requestedCurrency = ratesRQ.getCurrency();

        List<ExchangeRate> exchangeRates = validDirectories.stream()
                .map(directory -> nbpRatesService.call(directory.getFileName()))
                .map(retrieveAndMapToExchangeRatesFor(requestedCurrency))
                .flatMap(Collection::stream)
                .collect(toList());

        if (exchangeRates.isEmpty()) {
            result = RATES_NOT_FOUND_FOR_GIVEN_CURRENCY;
        }

        Exchange exchange = new Exchange();

        exchange.setCurrency(requestedCurrency)
                .addExchangeRate(exchangeRates);

        return response
                .setExchange(exchange)
                .setResult(result);
    }

    private Function<NbpRatesRS, List<ExchangeRate>> retrieveAndMapToExchangeRatesFor(Currency requestedCurrency) {
        return nbpRatesRS -> {
            String requestedCurrencyCode = requestedCurrency.getCode();
            LocalDate publicationDate = nbpRatesRS.getPublicationDate();

            return nbpRatesRS.getRates()
                    .stream()
                    .filter(nbpRate -> requestedCurrencyCode.equalsIgnoreCase(nbpRate.getCurrencyCode()))
                    .map(mapToExchangeRateWithDate(publicationDate))
                    .collect(toList());
        };
    }

    private Function<NbpRate, ExchangeRate> mapToExchangeRateWithDate(LocalDate publicationDate) {
        return nbpRate -> new ExchangeRate()
                .setBuyingRate(BigDecimal.valueOf(nbpRate.getBuyingRate()))
                .setSellingRate(BigDecimal.valueOf(nbpRate.getSellingRate()))
                .setDate(publicationDate);
    }

    public RatesService setNbpRatesService(NbpRatesService nbpRatesService) {
        this.nbpRatesService = nbpRatesService;
        return this;
    }

    public RatesService setGetDirectoriesService(GetDirectoriesService getDirectoriesService) {
        this.getDirectoriesService = getDirectoriesService;
        return this;
    }
}
