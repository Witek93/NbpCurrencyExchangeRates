package services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.NbpRatesRS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;

public class NbpRatesService {

    public NbpRatesRS call(String path) {
        return createUrl(path)
                .map(this::readXml)
                .map(this::deserialize)
                .map(e -> {
                    System.out.println(e);
                    return e;
                })
                .orElse(new NbpRatesRS());
    }

    private NbpRatesRS deserialize(String rawXml) {
        XmlMapper mapper = new XmlMapper();
        try {
            return mapper.readValue(rawXml, NbpRatesRS.class);
        } catch (IOException e) {
            throw new RuntimeException("NbpRatesService was unable to deserialize response", e);
        }
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
