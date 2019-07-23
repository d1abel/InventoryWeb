package inventory.component;

import inventory.domain.entity.ComputerEntity;
import inventory.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ParserServiceImpl implements ParserService {

    private final FileParser fileParser;
    private final DatabaseParser databaseParser;

    @Autowired
    public ParserServiceImpl(FileParser fileParser, DatabaseParser databaseParser) {
        this.fileParser = fileParser;
        this.databaseParser = databaseParser;
    }

    @Override
    public void updateDB() {
        databaseParser.updateDB();
    }

    @Override
    public void updateComputer(ComputerEntity computer) {
        databaseParser.updateComputer(computer);
    }

    @Override
    public void addComputer(ComputerEntity computer) {
        databaseParser.addComputer(computer);
    }

    @Override
    public ComputerEntity readReport(File file) {
        return fileParser.readReport(file);
    }

    @Override
    public void updateCollectionFromReports() {
        fileParser.updateCollectionFromReports();
    }

    @Override
    public void updateByFile(File file) {
        databaseParser.updateComputer(fileParser.readReport(file));
    }
}
