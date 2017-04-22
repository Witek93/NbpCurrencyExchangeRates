package parser;

import model.ExchangeType;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PathParserTest {
    @Test
    public void shouldPass_withNonPrintableUnicodeCharacters() throws Exception {
        PathParser pathParser = new PathParser("\uFEFFc001z150102");

        DirectoryData directoryData = pathParser.parse();

        assertThat(directoryData.getId()).isEqualTo("001");
        assertThat(directoryData.getType()).isEqualTo(ExchangeType.C);
        assertThat(directoryData.getDate()).isEqualTo(LocalDate.of(2015, 1, 2));
        assertThat(directoryData.getDirectoryPath()).isEqualTo("c001z150102");
    }
}