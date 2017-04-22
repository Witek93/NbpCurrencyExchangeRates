import model.NbpRatesRS;
import org.junit.Test;
import services.NbpRatesService;

import static org.assertj.core.api.Assertions.assertThat;

public class NbpRatesAvailability {

    private static final String NBP_RATES_RETURNED_NOTHING = "Call to NBP Rates service should contain some rates";

    @Test
    public void shouldConnectTo_externalNbpRates() throws Exception {
        NbpRatesService nbpRatesService = new NbpRatesService();
        String validPath = "c073z070413";

        NbpRatesRS nbpRatesRS = nbpRatesService.call(validPath);

        assertThat(nbpRatesRS.getRates())
                .overridingErrorMessage(NBP_RATES_RETURNED_NOTHING)
                .isNotEmpty();
    }
}
