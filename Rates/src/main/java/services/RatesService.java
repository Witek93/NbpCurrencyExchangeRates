package services;

import extensions.DirectoriesDecorator;
import model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static model.Result.FAILURE;
import static model.Result.SUCCESS;

public class RatesService {

    private NbpRatesService nbpRatesService = new NbpRatesService();
    private GetDirectoriesService getDirectoriesService = new GetDirectoriesService();

    public RatesRS call(RatesRQ ratesRQ) {
        Currency requestedCurrency = ratesRQ.getCurrency();

        if (ratesRQ.getCurrency() == null) {
            return new RatesRS().setResult(FAILURE);
        }

        Collection<Year> years = ratesRQ.getDateRange().getYears();
        GetDirectoriesRS getDirectoriesRS = getDirectoriesService.call(years);

        DirectoriesDecorator decoratedDirectories = new DirectoriesDecorator(getDirectoriesRS);

        Exchange exchange = new Exchange().setCurrency(requestedCurrency);
        ratesRQ.getDateRange().getDays()
                .stream()
                .flatMap(decoratedDirectories::findForDate)
                .map(directory -> nbpRatesService.call(directory.getFileName()))
                .map(nbpRatesRS -> {
                    String requestedCurrencyCode = requestedCurrency.getCode();
                    LocalDate publicationDate = nbpRatesRS.getPublicationDate();
                    return nbpRatesRS.getRates()
                            .stream()
                            .filter(nbpRate -> requestedCurrencyCode.equalsIgnoreCase(nbpRate.getCurrencyCode()))
                            .map(nbpRate -> new ExchangeRate()
                                    .setBuyingRate(BigDecimal.valueOf(nbpRate.getBuyingRate()))
                                    .setSellingRate(BigDecimal.valueOf(nbpRate.getSellingRate()))
                                    .setDate(publicationDate))
                            .collect(toList());
                })
                .flatMap(Collection::stream)
                .forEach(exchange::addExchangeRate);

        return new RatesRS()
                .setExchange(exchange)
                .setResult(SUCCESS);
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
