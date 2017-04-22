import model.GetDirectoriesRS;
import model.GetNbpDirectoriesRS;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import services.GetDirectoriesService;
import services.GetNbpDirectoriesService;
import tests.categories.Slow;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

public class NbpDirectoriesAvailability {

    private static final Year PREVIOUS_YEAR = Year.now().minusYears(1);

    @Category(Slow.class)
    @Test
    public void shouldConnectTo_externalNbpDirectory() throws Exception
    {
        GetNbpDirectoriesService directoryService = new GetNbpDirectoriesService();

        GetNbpDirectoriesRS getNbpDirectoriesRS = directoryService.call(PREVIOUS_YEAR);

        assertThat(getNbpDirectoriesRS.getPaths()).isNotEmpty();
    }

    @Category(Slow.class)
    @Test
    public void shouldConnectTo_externalNbpDirectory_throughAdapter() throws Exception
    {
        GetDirectoriesService getDirectoriesService = new GetDirectoriesService();

        GetDirectoriesRS response = getDirectoriesService.call(PREVIOUS_YEAR);

        assertThat(response.getDirectories()).isNotEmpty();
    }
}
