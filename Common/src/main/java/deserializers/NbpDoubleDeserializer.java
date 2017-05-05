package deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import exceptions.DeserializationException;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NbpDoubleDeserializer extends JsonDeserializer<Double> {

    private static final String VALID_DECIMAL_NUMBER_REGEX = "\\d+(,\\d+)?";

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String textToDeserialize = jsonParser.getText();
        checkForInvalidCharacters(textToDeserialize);
        return parseDoubleFromString(textToDeserialize);
    }

    private Double parseDoubleFromString(String textToDeserialize) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = format.parse(textToDeserialize);
            return number.doubleValue();
        } catch (ParseException e) {
            throw new DeserializationException(e);
        }
    }

    private void checkForInvalidCharacters(String textToDeserialize) {
        if (!textToDeserialize.matches(VALID_DECIMAL_NUMBER_REGEX)) {
            throw new DeserializationException("Valid decimal number should contain only one comma and digits!");
        }
    }
}
