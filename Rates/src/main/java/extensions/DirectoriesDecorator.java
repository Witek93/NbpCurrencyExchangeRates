package extensions;

import model.Directory;
import model.GetDirectoriesRS;

import java.time.LocalDate;
import java.util.stream.Stream;

import static model.ExchangeType.C;

public class DirectoriesDecorator {
    private GetDirectoriesRS getDirectoriesRS;

    public DirectoriesDecorator(GetDirectoriesRS getDirectoriesRS) {
        this.getDirectoriesRS = getDirectoriesRS;
    }

    public Stream<Directory> findForDate(LocalDate date) {
        return getDirectoriesRS.getDirectories()
                .stream()
                .filter(directory -> directory.getType() == C)
                .filter(directory -> date.equals(directory.getDate()))
                .findAny()
                .map(Stream::of)
                .orElse(Stream.empty());
    }
}
