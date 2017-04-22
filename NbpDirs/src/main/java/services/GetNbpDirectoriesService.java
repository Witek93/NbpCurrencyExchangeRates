package services;

import model.GetNbpDirectoriesRS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

public class GetNbpDirectoriesService {

    public GetNbpDirectoriesRS call(Year year) {

        GetNbpDirectoriesRS response = new GetNbpDirectoriesRS();

        createUrl(year)
                .map(GetNbpDirectoriesService::readData)
                .ifPresent(response::setPaths);

        return response;
    }

    private Optional<URL> createUrl(Year year) {
        String url = "http://www.nbp.pl/kursy/xml/dir" + parseYearForUrl(year) + ".txt";
        try {
            return of(new URL(url));
        } catch (MalformedURLException e) {
            return empty();
        }
    }

    private static String parseYearForUrl(Year year) {
        return Year.now().equals(year) ? "" : year.toString();
    }

    private static List<String> readData(URL url) {
        try {
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            return new BufferedReader(inputReader)
                    .lines()
                    .collect(toList());
        } catch (IOException e) {
            return emptyList();
        }
    }
}
