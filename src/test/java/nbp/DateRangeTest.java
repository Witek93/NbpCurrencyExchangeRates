package nbp;

import queryRates.model.request.DateRange;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class DateRangeTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldFail_withNullStartDate() throws Exception {
        new DateRange(null, LocalDate.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFail_withNullEndDate() throws Exception {
        new DateRange(LocalDate.now(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFail_withStartDateAfterEndDate() throws Exception {
        LocalDate now = LocalDate.now();
        new DateRange(now.plusDays(1), now);
    }

    @Test
    public void getDays_shouldReturnOneDay_forRangeFromTodayToToday() throws Exception {
        LocalDate today = LocalDate.now();
        DateRange dateRange = new DateRange(today, today);

        Collection<LocalDate> days = dateRange.getDays();

        assertThat(days)
                .hasSize(1)
                .containsExactly(today);
    }

    @Test
    public void getDays_shouldReturnMultipleDays() throws Exception {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(42);

        DateRange dateRange = new DateRange(startDate, endDate);
        Collection<LocalDate> days = dateRange.getDays();

        assertThat(days)
                .containsOnlyOnce(startDate, endDate);
    }

    @Test
    public void getYears_shouldReturnMultipleYears() throws Exception {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(3);

        DateRange dateRange = new DateRange(startDate, endDate);
        Collection<Year> years = dateRange.getYears();

        assertThat(years)
                .containsOnlyOnce(Year.from(startDate), Year.from(endDate));
    }
}