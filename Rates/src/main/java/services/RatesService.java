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
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static model.RatesResult.FAILURE;
import static model.RatesResult.RATES_NOT_FOUND;
import static model.RatesResult.SUCCESS;

public class RatesService {

    private NbpRatesService nbpRatesService = new NbpRatesService();
    private GetDirectoriesService getDirectoriesService = new GetDirectoriesService();

    public RatesRS call(RatesRQ ratesRQ) {

        RatesRS response = new RatesRS();
        RatesResult result = SUCCESS;

        if (ratesRQ.getCurrency() == null) {
            result = FAILURE;
        } else {
            List<Directory> validDirectories = getValidDirectories(ratesRQ);

            List<ExchangeRate> exchangeRates = validDirectories.stream()
                    .flatMap(getExchangeRatesFor(ratesRQ.getCurrency()))
                    .collect(toList());

            if (exchangeRates.isEmpty()) {
                result = RATES_NOT_FOUND;
            }

            response.setExchange(new Exchange()
                    .setCurrency(ratesRQ.getCurrency())
                    .setExchangeRates(exchangeRates));
        }

        return response
                .setResult(result);
    }

    private Function<Directory, Stream<ExchangeRate>> getExchangeRatesFor(Currency requestedCurrency) {
        return directory -> {
            NbpRatesRS nbpRatesRS = nbpRatesService.call(directory.getFileName());

            String requestedCurrencyCode = requestedCurrency.getCode();
            LocalDate publicationDate = nbpRatesRS.getPublicationDate();

            return nbpRatesRS.getRates()
                    .stream()
                    .filter(nbpRate -> requestedCurrencyCode.equalsIgnoreCase(nbpRate.getCurrencyCode()))
                    .map(mapToExchangeRateWithDate(publicationDate));
        };
    }

    private List<Directory> getValidDirectories(RatesRQ ratesRQ) {
        Collection<Year> years = ratesRQ.getDateRange().getYears();
        GetDirectoriesRS getDirectoriesRS = getDirectoriesService.call(years);

        DirectoriesDecorator decoratedDirectories = new DirectoriesDecorator(getDirectoriesRS);

        return ratesRQ.getDateRange().getDays()
                .stream()
                .flatMap(decoratedDirectories::findForDate)
                .collect(toList());
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
