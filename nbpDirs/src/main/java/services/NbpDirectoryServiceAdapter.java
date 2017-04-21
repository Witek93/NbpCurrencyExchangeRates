package services;

import model.NbpDirectory;
import model.NbpDirectoryAdaptedResponse;
import model.NbpDirectoryResponse;
import parser.DirectoryData;
import parser.PathParser;

import java.time.Year;

public class NbpDirectoryServiceAdapter {
    private NbpDirectoryService nbpDirectoryService;

    public NbpDirectoryServiceAdapter(NbpDirectoryService nbpDirectoryService) {
        this.nbpDirectoryService = nbpDirectoryService;
    }

    public NbpDirectoryAdaptedResponse call(Year year) {
        NbpDirectoryAdaptedResponse response = new NbpDirectoryAdaptedResponse();

        NbpDirectoryResponse nbpDirectoryResponse = nbpDirectoryService.call(year);

        nbpDirectoryResponse.getPaths()
                .stream()
                .map(this::createNbpDirectory)
                .forEach(response::addDirectory);

        return response;
    }

    private NbpDirectory createNbpDirectory(String path) {
        PathParser parser = new PathParser(path);

        DirectoryData directoryData = parser.parse();

        return new NbpDirectory()
                .setId(directoryData.getId())
                .setType(directoryData.getType())
                .setDate(directoryData.getDate())
                .setFileName(directoryData.getDirectoryPath());
    }

}
