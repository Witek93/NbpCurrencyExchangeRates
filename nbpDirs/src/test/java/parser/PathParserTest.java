package parser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PathParserTest {
    @Test
    public void shouldPass_withNonPrintableUnicodeCharacters() throws Exception {
        PathParser pathParser = new PathParser("\uFEFFc001z150102");

        assertThat(pathParser.getId()).isEqualTo("001");
    }
}