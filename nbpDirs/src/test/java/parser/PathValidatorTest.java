package parser;

import exceptions.InvalidDataException;
import org.junit.Test;

public class PathValidatorTest {
    PathValidator pathValidator = new PathValidator();

    @Test
    public void shouldPass() {
        pathValidator.validate("h252z141231");
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_forIncorrectType_X() {
        pathValidator.validate("X252z141231");
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_forOutOfRangeId_1234() {
        pathValidator.validate("h1234z141231");
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_forIncorrectSeparator_q() {
        pathValidator.validate("X252q141231");
    }

    @Test(expected = InvalidDataException.class)
    public void shouldFail_forInvalidDate() {
        pathValidator.validate("X252z999999");
    }

}