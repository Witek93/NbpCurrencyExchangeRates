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
import java.util.function.Function;
import java.util.logging.Logger;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static model.NbpRatesResult.FAILURE;
import static model.NbpRatesResult.SUCCESS;

public class NbpRatesService {

    private static Logger logger = Logger.getLogger(NbpRatesService.class.getName());

    public NbpRatesRS call(String path) {
        logger.info(path);
        try {
            return createUrl(path)
                    .map(this::readXml)
                    .map(deserializeTo(NbpRatesRS.class))
                    .map(response -> response.setResult(SUCCESS))
                    .orElse(new NbpRatesRS().setResult(FAILURE));
        } catch (Exception e) {
            e.printStackTrace();
            return new NbpRatesRS().setResult(FAILURE);
        }
    }

    private <T> Function<String, T> deserializeTo(Class<T> valueType) {
        return rawXml -> {
            XmlMapper mapper = new XmlMapper();
            try {
                return mapper.readValue(rawXml, valueType);
            } catch (IOException e) {
                throw new RuntimeException("Cannot deserialize to: " + valueType.getName(), e);
            }
        };
    }

    private String readXml(URL url) {
        try {
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            return new BufferedReader(inputReader)
                    .lines()
                    .collect(joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Optional<URL> createUrl(String path) {
        String url = "http://www.nbp.pl/kursy/xml/" + path + ".xml";
        try {
            return of(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return empty();
        }
    }
}
