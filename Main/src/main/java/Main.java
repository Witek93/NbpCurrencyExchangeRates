import model.QueryRatesRequest;
import model.request.DateRange;
import parser.InputData;
import parser.InputDataParser;
import processors.Parser;
import services.NbpDirectoryService;
import services.NbpDirectoryServiceAdapter;

import java.time.Year;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        args = new String[]{"EUR", "2013-01-28", "2015-01-31"};

        Parser<InputData> parser = new InputDataParser(args);
        InputData inputData = parser.parse();

        QueryRatesRequest queryRatesRequest = new QueryRatesRequest()
                .setCurrency(inputData.getCurrency())
                .setDateRange(new DateRange(inputData.getStartDate(), inputData.getEndDate()));

        Collection<Year> years = queryRatesRequest.getDateRange().getYears();

        NbpDirectoryService directoryService = new NbpDirectoryService();
        NbpDirectoryServiceAdapter nbpDirectoryServiceAdapter = new NbpDirectoryServiceAdapter(directoryService);

        years.stream()
                .map(nbpDirectoryServiceAdapter::call)
                .flatMap(e -> e.getDirectories().stream())
                .forEach(e -> System.out.println(e));

    }
}
