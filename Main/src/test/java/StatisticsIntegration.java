import model.StatisticsRS;
import org.junit.Test;
import services.StatisticsService;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static model.StatisticsResult.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsIntegration {
    @Test
    public void performCall_withSuccess() throws Exception {
        BigDecimal expectedAverage = valueOf(3.7976);
        StatisticsService service = new StatisticsService();

        StatisticsRS statisticsRS = service.call("EUR", "2007-04-13", "2007-04-13");

        assertThat(statisticsRS).isNotNull();
        assertThat(statisticsRS.getResult()).isEqualTo(SUCCESS);
        assertThat(statisticsRS.getAverage())
                .overridingErrorMessage("Expected average: " + expectedAverage + ", but was: " +statisticsRS.getAverage())
                .isEqualByComparingTo(expectedAverage);
        assertThat(statisticsRS.getStandardDeviation())
                .overridingErrorMessage("Expected Standard deviation: 0, but was: " + statisticsRS.getStandardDeviation())
                .isEqualByComparingTo(ZERO);
    }
}