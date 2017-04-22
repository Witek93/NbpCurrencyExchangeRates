import model.DateRange;
import model.RatesRQ;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import parser.InputData;
import parser.InputDataParser;
import processors.Parser;
import services.GetDirectoriesService;
import tests.categories.Slow;

import java.time.Year;
import java.util.Collection;

public class E2eTests {
    @Category(Slow.class)
    @Test
    public void foo() {
        String[] args = {"EUR", "2013-01-28", "2015-01-31"};
        Parser<InputData> parser = new InputDataParser(args);
        InputData inputData = parser.parse();

        RatesRQ ratesRQ = new RatesRQ()
                .setCurrency(inputData.getCurrency())
                .setDateRange(new DateRange(inputData.getStartDate(), inputData.getEndDate()));

        Collection<Year> years = ratesRQ.getDateRange().getYears();

        GetDirectoriesService getDirectoriesService = new GetDirectoriesService();

        years.stream()
                .map(getDirectoriesService::call)
                .flatMap(e -> e.getDirectories().stream())
                .forEach(e -> System.out.println(e));

    }
}
