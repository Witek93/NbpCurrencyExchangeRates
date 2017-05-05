package assertions;

import model.ExchangeType;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import parser.DirectoryData;

import java.time.LocalDate;

public class DirectoryDataAssertion extends AbstractAssert<DirectoryDataAssertion, DirectoryData> {
    private DirectoryDataAssertion(DirectoryData actual) {
        super(actual, DirectoryDataAssertion.class);
    }

    public static DirectoryDataAssertion assertThat(DirectoryData actual) {
        return new DirectoryDataAssertion(actual);
    }

    public DirectoryDataAssertion hasId(String expectedId) {
        Assertions.assertThat(actual.getId()).isEqualTo(expectedId);
        return this;
    }

    public DirectoryDataAssertion hasType(ExchangeType expectedType) {
        Assertions.assertThat(actual.getType()).isEqualTo(expectedType);
        return this;
    }

    public DirectoryDataAssertion hasDate(LocalDate expectedDate) {
        Assertions.assertThat(actual.getDate()).isEqualTo(expectedDate);
        return this;
    }

    public DirectoryDataAssertion hasDirectoryPath(String directoryPath) {
        Assertions.assertThat(actual.getDirectoryPath()).isEqualTo(directoryPath);
        return this;
    }
}
