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
                .map(PathParser::new)
                .map(parser -> new NbpDirectory()
                        .setId(parser.getId())
                        .setType(parser.getType())
                        .setDate(parser.getDate())
                        .setFileName(parser.getDirectoryPath()))
                .forEach(response::addDirectory);

        return response;
    }


}
