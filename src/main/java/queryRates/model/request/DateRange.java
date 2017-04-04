package queryRates.model.request;

import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.range;

public class DateRange {
    private final LocalDate startDate, endDate;

    public DateRange(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        validateInputDates(startDateInclusive, endDateExclusive);

        this.startDate = startDateInclusive;
        this.endDate = endDateExclusive;
    }

    private void validateInputDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("DateRange cannot be created with null dates.");
        } else if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("DateRange cannot begin after its end.");
        }
    }

    public Collection<LocalDate> getDays() {
        return range(0, DAYS.between(startDate, endDate) + 1)
                .mapToObj(startDate::plusDays)
                .collect(toList());
    }

    public Collection<Year> getYears() {
        Year yearOfStartDate = Year.from(startDate);

        return range(0, YEARS.between(startDate, endDate) + 1)
                .mapToObj(yearOfStartDate::plusYears)
                .collect(toList());
    }
}
