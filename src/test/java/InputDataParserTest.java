import queryRates.parser.InvalidDataException;
import queryRates.model.QueryRatesRequest;
import org.junit.Test;
import queryRates.parser.InputDataParser;

import static org.assertj.core.api.Assertions.assertThat;

public class InputDataParserTest {
    private static String VALID_CURRENCY = "EUR";
    private static String VALID_START_DATE = "2013-01-01";
    private static String VALID_END_DATE = "2013-12-31";

    @Test(expected = IllegalArgumentException.class)
    public void shouldFail_withTooManyArguments() throws Exception {
        new InputDataParser("too", "many", "arguments", "passed");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFail_withNoArguments() throws Exception {
        new InputDataParser();
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_withInvalidCurrency() throws Exception {
        InputDataParser parser = new InputDataParser("invalid-currency-code", VALID_START_DATE, VALID_END_DATE);

        parser.parse();
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_withInvalidStartDate() throws Exception {
        InputDataParser parser = new InputDataParser(VALID_CURRENCY, "invalid-date", VALID_END_DATE);

        parser.parse();
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_withInvalidEndDate() throws Exception {
        InputDataParser parser = new InputDataParser(VALID_CURRENCY, VALID_START_DATE, "invalid-date");

        parser.parse();
    }

    @Test
    public void shouldPass_whenParsingValidData() throws Exception {
        InputDataParser parser = new InputDataParser(VALID_CURRENCY, VALID_START_DATE, VALID_END_DATE);

        QueryRatesRequest queryRatesRequest = parser.parse();

        assertThat(queryRatesRequest).isNotNull();
    }


}