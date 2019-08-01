package inventory.service;

import inventory.domain.entity.ComputerEntity;

import java.io.File;

public interface ParserService {

    void updateDB();

    void updateComputer(ComputerEntity computer);

    void addComputer(ComputerEntity computer);

    ComputerEntity readReport(File file);

    void updateCollectionFromReports();

    void updateByFile(File file);

}
