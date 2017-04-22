package services;

import model.NbpRatesRS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;

public class NbpRatesService {

    public NbpRatesRS call(String path) {
        NbpRatesRS response = new NbpRatesRS();

        createUrl(path)
                .map(this::readXml)
                .ifPresent(System.out::println);

        return response;
    }

    private String readXml(URL url) {
        try {
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            return new BufferedReader(inputReader)
                    .lines()
                    .collect(joining());
        } catch (IOException e) {
            return null;
        }

    }

    private Optional<URL> createUrl(String path) {
        String url = "http://www.nbp.pl/kursy/xml/" + path + ".xml";
        try {
            return of(new URL(url));
        } catch (MalformedURLException e) {
            return empty();
        }
    }


}
