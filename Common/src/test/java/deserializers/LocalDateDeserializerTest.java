package deserializers;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalDateDeserializerTest {
    @Test
    public void shouldPass_whenDeserializingLocalDate_forDateInIsoFormat() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText())
                .thenReturn("2017-04-22");

        LocalDate deserializedDate = new LocalDateDeserializer().deserialize(jsonParser, null);

        assertThat(deserializedDate)
                .isEqualTo(LocalDate.of(2017, 4, 22));
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrow_whenDeserializingLocalDate_forInvalidFormat() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText())
                .thenReturn("99-12-31");

        new LocalDateDeserializer().deserialize(jsonParser, null);
    }

}