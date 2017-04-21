package parser;


import exceptions.InvalidDataException;
import model.Currency;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class InputDataParserTest {
    private static String RAW_VALID_CURRENCY = "EUR";
    private static Currency VALID_CURRENCY = new Currency("Eur");
    private static String RAW_VALID_START_DATE = "2013-01-01";
    private static LocalDate VALID_START_DATE = LocalDate.of(2013, 1, 1);
    private static String RAW_VALID_END_DATE = "2013-12-31";
    private static LocalDate VALID_END_DATE = LocalDate.of(2013, 12, 31);

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
        InputDataParser parser = new InputDataParser("invalid-currency-code", RAW_VALID_START_DATE, RAW_VALID_END_DATE);

        parser.parse();
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_withInvalidStartDate() throws Exception {
        InputDataParser parser = new InputDataParser(RAW_VALID_CURRENCY, "invalid-date", RAW_VALID_END_DATE);

        parser.parse();
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_withInvalidEndDate() throws Exception {
        InputDataParser parser = new InputDataParser(RAW_VALID_CURRENCY, RAW_VALID_START_DATE, "invalid-date");

        parser.parse();
    }

    @Test
    public void shouldPass_whenParsingValidData() throws Exception {
        // given
        InputDataParser parser = new InputDataParser(RAW_VALID_CURRENCY, RAW_VALID_START_DATE, RAW_VALID_END_DATE);

        // when
        InputData inputData = parser.parse();

        // then
        assertThat(inputData.getCurrency()).isEqualTo(VALID_CURRENCY);
        assertThat(inputData.getStartDate()).isEqualTo(VALID_START_DATE);
        assertThat(inputData.getEndDate()).isEqualTo(VALID_END_DATE);
    }
}