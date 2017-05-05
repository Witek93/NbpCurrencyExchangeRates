package parser;

import model.ExchangeType;
import org.junit.Test;

import java.time.LocalDate;

import static assertions.DirectoryDataAssertion.assertThat;

public class PathParserTest {
    @Test
    public void shouldPass_withNonPrintableUnicodeCharacters() throws Exception {
        PathParser pathParser = new PathParser("\uFEFFc001z150102");

        DirectoryData directoryData = pathParser.parse();

        assertThat(directoryData)
                .hasId("001")
                .hasType(ExchangeType.C)
                .hasDate(LocalDate.of(2015, 1, 2))
                .hasDirectoryPath("c001z150102");
    }
}