import model.NbpDirectoryAdaptedResponse;
import model.NbpDirectoryResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import services.NbpDirectoryService;
import services.NbpDirectoryServiceAdapter;
import tests.categories.Slow;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

public class NbpAvailabilityTests {

    private static final Year PREVIOUS_YEAR = Year.now().minusYears(1);

    @Category(Slow.class)
    @Test
    public void shouldConnectTo_externalNbpDirectory() throws Exception
    {
        NbpDirectoryService directoryService = new NbpDirectoryService();

        NbpDirectoryResponse nbpDirectoryResponse = directoryService.call(PREVIOUS_YEAR);

        assertThat(nbpDirectoryResponse.getPaths()).isNotEmpty();
    }

    @Category(Slow.class)
    @Test
    public void shouldConnectTo_externalNbpDirectory_throughAdapter() throws Exception
    {
        NbpDirectoryService directoryService = new NbpDirectoryService();
        NbpDirectoryServiceAdapter adapter = new NbpDirectoryServiceAdapter(directoryService);

        NbpDirectoryAdaptedResponse response = adapter.call(PREVIOUS_YEAR);

        assertThat(response.getDirectories()).isNotEmpty();
    }
}
