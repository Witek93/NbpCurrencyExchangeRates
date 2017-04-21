import model.QueryRatesRequest;
import model.request.DateRange;
import parser.InputDataParser;
import services.NbpDirectoryService;
import services.NbpDirectoryServiceAdapter;

import java.time.Year;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        args = new String[]{"EUR", "2013-01-28", "2015-01-31"};

        InputDataParser parser = new InputDataParser(args);
        parser.parse();

        QueryRatesRequest queryRatesRequest = new QueryRatesRequest()
                .setCurrency(parser.getCurrency())
                .setDateRange(new DateRange(parser.getStartDate(), parser.getEndDate()));

        Collection<Year> years = queryRatesRequest.getDateRange().getYears();

        NbpDirectoryService directoryService = new NbpDirectoryService();
        NbpDirectoryServiceAdapter nbpDirectoryServiceAdapter = new NbpDirectoryServiceAdapter(directoryService);

        years.stream()
                .map(nbpDirectoryServiceAdapter::call)
                .flatMap(e -> e.getDirectories().stream())
                .forEach(e -> System.out.println(e));

    }
}
