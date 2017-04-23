import model.StatisticsRS;
import org.junit.Test;
import services.StatisticsService;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static model.RatesResult.RATES_NOT_FOUND;
import static model.StatisticResults.ratesServiceFailure;
import static model.StatisticResults.success;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsIntegration {
    @Test
    public void performCall_withSuccess() throws Exception {
        BigDecimal expectedAverage = valueOf(3.7976);
        StatisticsService service = new StatisticsService();

        StatisticsRS statisticsRS = service.call("EUR", "2007-04-13", "2007-04-13");

        assertThat(statisticsRS).isNotNull();
        assertThat(statisticsRS.getResult()).isEqualTo(success());
        assertThat(statisticsRS.getAverage())
                .overridingErrorMessage("Expected average: " + expectedAverage + ", but was: " +statisticsRS.getAverage())
                .isEqualByComparingTo(expectedAverage);
        assertThat(statisticsRS.getStandardDeviation())
                .overridingErrorMessage("Expected Standard deviation: 0, but was: " + statisticsRS.getStandardDeviation())
                .isEqualByComparingTo(ZERO);
    }

    @Test
    public void successfulCall_forDateRange() throws Exception {
        StatisticsService service = new StatisticsService();

        StatisticsRS statisticsRS = service.call("EUR", "2007-04-13", "2007-05-13");

        assertThat(statisticsRS).isNotNull();
        assertThat(statisticsRS.getResult()).isEqualTo(success());
        assertThat(statisticsRS.getAverage())
                .isNotEqualByComparingTo(ZERO);
        assertThat(statisticsRS.getStandardDeviation())
                .overridingErrorMessage("Expected Standard deviation not equal to 0, but was: " + statisticsRS.getStandardDeviation())
                .isNotEqualByComparingTo(ZERO);
    }
    @Test
    public void callFailure_forInvalidCurrency() throws Exception {
        StatisticsService service = new StatisticsService();

        StatisticsRS statisticsRS = service.call("XXX", "2007-04-13", "2007-04-13");

        assertThat(statisticsRS).isNotNull();
        assertThat(statisticsRS.getResult()).isEqualTo(ratesServiceFailure(RATES_NOT_FOUND));
    }
}
