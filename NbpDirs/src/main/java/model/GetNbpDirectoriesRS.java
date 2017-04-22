package model;

import java.util.ArrayList;
import java.util.Collection;

public class GetNbpDirectoriesRS {
    private Collection<String> paths;

    public Collection<String> getPaths() {
        if (paths == null)
        {
            paths = new ArrayList<>();
        }
        return paths;
    }

    public GetNbpDirectoriesRS setPaths(Collection<String> paths) {
        this.paths = paths;
        return this;
    }

    @Override
    public String toString() {
        return "GetNbpDirectoriesRS{" +
                "paths=" + paths +
                '}';
    }

}
