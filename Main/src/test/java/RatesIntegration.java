import model.*;
import org.junit.Test;
import services.RatesService;

import static java.time.LocalDate.now;
import static model.RatesResult.RATES_NOT_FOUND;
import static model.RatesResult.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;

public class RatesIntegration {
    private static final DateRange PREVIOUS_MONTH = new DateRange(now().minusMonths(1), now());
    private static final DateRange SHORT_RANGE = new DateRange(now().minusDays(1), now());

    @Test
    public void performCall_withSuccess() throws Exception {
        RatesService ratesService = new RatesService();
        RatesRQ ratesRQ = new RatesRQ()
                .setCurrency(new Currency("USD"))
                .setDateRange(PREVIOUS_MONTH);

        RatesRS ratesRS = ratesService.call(ratesRQ);

        assertThat(ratesRS).isNotNull();
        assertThat(ratesRS.getResult()).isEqualTo(SUCCESS);
        assertThat(ratesRS.getExchange().getExchangeRates()).isNotEmpty();
    }

    @Test
    public void failGracefully_forInvalidCurrency() throws Exception {
        RatesService ratesService = new RatesService();
        RatesRQ ratesRQ = new RatesRQ()
                .setCurrency(new Currency("CEBULION"))
                .setDateRange(SHORT_RANGE);

        RatesRS ratesRS = ratesService.call(ratesRQ);

        assertThat(ratesRS).isNotNull();
        assertThat(ratesRS.getResult()).isEqualTo(RATES_NOT_FOUND);
        assertThat(ratesRS.getExchange().getExchangeRates()).isNullOrEmpty();
    }
}
