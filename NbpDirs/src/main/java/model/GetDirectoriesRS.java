package model;

import java.util.ArrayList;
import java.util.Collection;

public class GetDirectoriesRS {
    private Collection<Directory> directories;

    public Collection<Directory> getDirectories() {
        if (directories == null) {
            directories = new ArrayList<>();
        }
        return directories;
    }

    public GetDirectoriesRS addDirectory(Directory directory) {
        if (directory != null) {
            getDirectories().add(directory);
        }
        return this;
    }

    public GetDirectoriesRS addDirectories(Collection<Directory> directories) {
        if (directories != null) {
            getDirectories().addAll(directories);
        }
        return this;
    }
}
