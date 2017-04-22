package deserializers;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalDateDeserializerTest {
    @Test
    public void parsesLocalDate_forIsoFormat() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText())
                .thenReturn("2017-04-22");

        LocalDate deserializedDate = new LocalDateDeserializer().deserialize(jsonParser, null);

        assertThat(deserializedDate)
                .isEqualTo(LocalDate.of(2017, 4, 22));
    }
}