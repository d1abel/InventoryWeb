package inventory.component;

import inventory.component.config.ReadConfigurationFile;
import inventory.component.fileToObject.ComputerParametersFactory;
import inventory.component.fileToObject.ComputerParametersService;
import inventory.domain.entity.ComputerEntity;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Component
public class FileParser {

    private Collection<ComputerEntity> computers = new ArrayList<>();

    private final ComputerParametersFactory factory;
    private final ReadConfigurationFile configurationFile;

    @Autowired
    public FileParser(ComputerParametersFactory factory, ReadConfigurationFile configurationFile) {
        this.factory = factory;
        this.configurationFile = configurationFile;
    }


    @SneakyThrows
    private Collection<File> getReports() {
        String filepath = configurationFile.getSettings().get("reports.dir");

        return Files.walk(Paths.get(filepath), 1)
                .map(Path::toFile)
                .filter(File::isFile)
                .collect(Collectors.toList());
    }

    private void getComputers() {
        computers.clear();
        for (File file : getReports()) {
//            Thread thread = new Thread(new ReadReportThread(file));
//            thread.start();
            readReport(file);
        }
    }

    @SneakyThrows
    ComputerEntity readReport(File file) {
        ComputerEntity pc = new ComputerEntity();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "cp1251"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("=");
                ComputerParametersService service = factory.getParameter(split[0]);
                if (service != null) {
                    service.readRow(pc, split[1]);
                }
            }
        }
        computers.add(pc);
        return pc;
    }

    Collection<ComputerEntity> getComputersCollection() {
        return computers;
    }

    void updateCollectionFromReports() {
        getComputers();
    }

    private void getUsersInfo() {
        //TODO: connect to ldap and get info
    }

}
