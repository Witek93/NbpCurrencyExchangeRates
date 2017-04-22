package services;

import model.Directory;
import model.GetNbpDirectoriesRS;
import model.GetDirectoriesRS;
import parser.DirectoryData;
import parser.PathParser;

import java.time.Year;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class GetDirectoriesService {
    private GetNbpDirectoriesService getNbpDirectoriesService;

    public GetDirectoriesService(GetNbpDirectoriesService getNbpDirectoriesService) {
        this.getNbpDirectoriesService = getNbpDirectoriesService;
    }

    public GetDirectoriesRS call(Year ... years) {
        return call(asList(years));
    }

    public GetDirectoriesRS call(Collection<Year> years) {
        GetDirectoriesRS response = new GetDirectoriesRS();

        List<Directory> nbpDirectories = years.stream()
                .map(getNbpDirectoriesService::call)
                .map(GetNbpDirectoriesRS::getPaths)
                .flatMap(Collection::stream)
                .map(this::createNbpDirectory)
                .collect(toList());

        response.addDirectories(nbpDirectories);

        return response;
    }

    private Directory createNbpDirectory(String path) {
        PathParser parser = new PathParser(path);

        DirectoryData directoryData = parser.parse();

        return new Directory()
                .setId(directoryData.getId())
                .setType(directoryData.getType())
                .setDate(directoryData.getDate())
                .setFileName(directoryData.getDirectoryPath());
    }

}
