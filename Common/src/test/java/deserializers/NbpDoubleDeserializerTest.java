package deserializers;

import com.fasterxml.jackson.core.JsonParser;
import exceptions.DeserializationException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NbpDoubleDeserializerTest {

    private static final Double VALID_DOUBLE = Double.valueOf("1.234");

    private static final String VALID_DECIMAL_NUMBER = "1,234";
    private static final String INVALID_DECIMAL_NUMBER_WITH_DOT = "1.234";
    private static final String INVALID_DECIMAL_NUMBER_WITH_TOO_MANY_COMMAS = "1,234,56";

    @Test
    public void shouldPass_whenDeserializingDouble_withComma() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText())
                .thenReturn(VALID_DECIMAL_NUMBER);

        Double deserializedDouble = new NbpDoubleDeserializer().deserialize(jsonParser, null);

        assertThat(deserializedDouble)
                .isEqualTo(VALID_DOUBLE);
    }

    @Test(expected = DeserializationException.class)
    public void shouldThrow_whenDeserializingDouble_withTwoCommas() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText())
                .thenReturn(INVALID_DECIMAL_NUMBER_WITH_TOO_MANY_COMMAS);

        new NbpDoubleDeserializer().deserialize(jsonParser, null);
    }


    @Test(expected = DeserializationException.class)
    public void shouldThrow_whenDeserializingDouble_withDot() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText())
                .thenReturn(INVALID_DECIMAL_NUMBER_WITH_DOT);

        new NbpDoubleDeserializer().deserialize(jsonParser, null);
    }
}