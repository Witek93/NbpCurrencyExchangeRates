import model.NbpRatesRS;
import org.junit.Test;
import services.NbpRatesService;

import static model.RatesResult.FAILURE;
import static model.RatesResult.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;

public class NbpRatesAvailability {

    private static final String NBP_RATES_RETURNED_NOTHING = "Call to NBP Rates service should contain some rates";

    @Test
    public void shouldConnectTo_externalNbpRates() throws Exception {
        NbpRatesService nbpRatesService = new NbpRatesService();
        String validPath = "c073z070413";

        NbpRatesRS nbpRatesRS = nbpRatesService.call(validPath);

        assertThat(nbpRatesRS.getResult()).isEqualTo(SUCCESS);
        assertThat(nbpRatesRS.getRates())
                .overridingErrorMessage(NBP_RATES_RETURNED_NOTHING)
                .isNotEmpty();
    }

    @Test
    public void shouldReturnErrorMessage_forNonExistingPath() throws Exception {
        NbpRatesService nbpRatesService = new NbpRatesService();
        String invalidPath = "c999z999999";

        NbpRatesRS nbpRatesRS = nbpRatesService.call(invalidPath);

        assertThat(nbpRatesRS.getRates()).isNullOrEmpty();
        assertThat(nbpRatesRS.getResult()).isEqualTo(FAILURE);
    }
}
