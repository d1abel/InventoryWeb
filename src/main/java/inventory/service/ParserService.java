package inventory.service;

import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.OsEntity;
import inventory.domain.entity.PcUserEntity;
import inventory.domain.entity.ProcessorEntity;

import java.io.File;
import java.util.Collection;

public interface ParserService {

    void updateDB();

    void updateComputer(ComputerEntity computer);

    void addComputer(ComputerEntity computer);

    ComputerEntity readReport(File file);

    void updateCollectionFromReports();

    void updateByFile(File file);

}
