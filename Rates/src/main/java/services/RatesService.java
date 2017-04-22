package services;

import extensions.DirectoriesDecorator;
import model.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static model.Result.*;

public class RatesService {

    private NbpRatesService nbpRatesService = new NbpRatesService();
    private GetDirectoriesService getDirectoriesService = new GetDirectoriesService();

    public RatesRS call(RatesRQ ratesRQ) {
        Currency requestedCurrency = ratesRQ.getCurrency();

        if(ratesRQ.getCurrency() == null) {
            return new RatesRS().setResult(FAILURE);
        }

        Collection<Year> years = ratesRQ.getDateRange().getYears();
        GetDirectoriesRS getDirectoriesRS = getDirectoriesService.call(years);

        // DECORATED for C type only
        DirectoriesDecorator decoratedDirectories = new DirectoriesDecorator(getDirectoriesRS);

        Exchange exchange = new Exchange().setCurrency(requestedCurrency);
        ratesRQ.getDateRange().getDays()
                .stream()
                .flatMap(decoratedDirectories::findForDate)
                .map(directory -> nbpRatesService.call(directory.getFileName()))
                .map(getRatesForCurrency(requestedCurrency))
                .flatMap(Collection::stream)
                .map(this::mapToExchange)
                .forEach(exchange::addExchangeRate);

        return new RatesRS()
                .setExchange(exchange)
                .setResult(SUCCESS);
    }

    private Function<NbpRatesRS, List<NbpRate>> getRatesForCurrency(Currency requestedCurrency) {
        return nbpRatesRS -> {
            List<NbpRate> result = nbpRatesRS.getRates()
                    .stream()
                    .filter(nbpRate -> requestedCurrency.getCode().equalsIgnoreCase(nbpRate.getCurrencyCode()))
                    .collect(toList());
            return result;
        };
    }

    private ExchangeRate mapToExchange(NbpRate nbpRate) {
        return new ExchangeRate()
                .setBuyingRate(BigDecimal.valueOf(nbpRate.getBuyingRate()))
                .setSellingRate(BigDecimal.valueOf(nbpRate.getSellingRate()))
                .setDate(null); // TODO
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
