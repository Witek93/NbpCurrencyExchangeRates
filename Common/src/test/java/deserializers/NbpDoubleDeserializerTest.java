package deserializers;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NbpDoubleDeserializerTest {
    @Test
    public void parsesDouble_withComma() throws Exception {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText())
                .thenReturn("1,234");

        Double deserializedDouble = new NbpDoubleDeserializer().deserialize(jsonParser, null);

        assertThat(deserializedDouble)
                .isEqualTo(Double.valueOf("1.234"));
    }
}