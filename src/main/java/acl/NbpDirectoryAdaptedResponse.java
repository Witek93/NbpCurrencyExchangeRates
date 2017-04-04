package acl;

import java.util.ArrayList;
import java.util.Collection;

public class NbpDirectoryAdaptedResponse {
    private Collection<NbpDirectory> directories;

    public Collection<NbpDirectory> getDirectories() {
        if (directories == null) {
            directories = new ArrayList<>();
        }
        return directories;
    }

    public NbpDirectoryAdaptedResponse addDirectory(NbpDirectory directory) {
        if (directory != null) {
            getDirectories().add(directory);
        }
        return this;
    }

    public NbpDirectoryAdaptedResponse setDirectories(Collection<NbpDirectory> directories) {
        this.directories = directories;
        return this;
    }
}
