package services;

import model.NbpDirectory;
import model.NbpDirectoryAdaptedResponse;
import model.NbpDirectoryResponse;
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

        return new NbpDirectory()
                .setId(parser.getId())
                .setType(parser.getType())
                .setDate(parser.getDate())
                .setFileName(parser.getDirectoryPath());
    }


}
