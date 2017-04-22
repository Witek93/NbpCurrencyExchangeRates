package deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NbpDoubleDeserializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        try {
            Number number = format.parse(jsonParser.getText());
            return number.doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
