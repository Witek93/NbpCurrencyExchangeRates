package parser;

import model.ExchangeType;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PathParserTest {
    @Test
    public void shouldPass_withNonPrintableUnicodeCharacters() throws Exception {
        PathParser pathParser = new PathParser("\uFEFFc001z150102");

        assertThat(pathParser.getId()).isEqualTo("001");
        assertThat(pathParser.getType()).isEqualTo(ExchangeType.C);
        assertThat(pathParser.getDate()).isEqualTo(LocalDate.of(2015, 1, 2));
        assertThat(pathParser.getDirectoryPath()).isEqualTo("c001z150102");
    }
}