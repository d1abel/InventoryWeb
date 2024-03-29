package inventory.component;

import inventory.domain.entity.*;
import inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DatabaseParser {

    private final ComputerRepository computerRepository;
    private final OsRepository osRepository;
    private final PcUserRepository pcUserRepository;
    private final ProcessorRepository processorRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final FileParser fileParser;

    @Autowired
    public DatabaseParser(ComputerRepository computerRepository, OsRepository osRepository, PcUserRepository pcUserRepository, ProcessorRepository processorRepository, UserHistoryRepository userHistoryRepository, FileParser fileParser) {
        this.computerRepository = computerRepository;
        this.osRepository = osRepository;
        this.pcUserRepository = pcUserRepository;
        this.processorRepository = processorRepository;
        this.userHistoryRepository = userHistoryRepository;
        this.fileParser = fileParser;
    }

    void updateDataBaseFromReportsDirectory() {

        fileParser.updateCollectionFromReports();
        for (ComputerEntity computer : fileParser.getComputersCollection()) {
            updateComputer(computer);
        }
    }

    @Transactional
    void updateComputer(ComputerEntity computer) {
        ComputerEntity entity = computerRepository.findByMac(computer.getMac());
        if (entity == null) {
            addComputer(computer);
            return;
        }
        if (entity.getLastNames() != null) {
            if (entity.getLastNames().contains(computer.getPcname())) {
                return;
            }
        }
        if (!(computer.getPcname().equalsIgnoreCase(entity.getPcname()))) {
            if (entity.getLastNames() == null) {
                computer.setLastNames(entity.getPcname());
            } else {
                computer.setLastNames(entity.getLastNames() + ", " + entity.getPcname());
            }
        }

        if (!(computer.getLoggedUser().getUserLogin().equalsIgnoreCase(entity.getLoggedUser().getUserLogin()))) {
            Optional<UserHistoryEntity> historyEntity = userHistoryRepository.findByPcAndCurrentuserAndEndDate(entity.getPcname(), entity.getLoggedUser().getUserLogin(), null);
            if (historyEntity.isPresent()) {
                UserHistoryEntity history = historyEntity.get();
                history.setEndDate(LocalDate.now());
                userHistoryRepository.save(history);
            }
            userHistoryRepository.save(new UserHistoryEntity(computer.getPcname(), computer.getLoggedUser().getUserLogin(), LocalDate.now(), null));
        }

        updateOSAndUserAndProcessor(computer);
        computer.setId(entity.getId());
        computerRepository.save(computer);
    }

    @Transactional
    void addComputer(ComputerEntity computer) {
        updateOSAndUserAndProcessor(computer);
        computerRepository.save(computer);
        userHistoryRepository.save(new UserHistoryEntity(computer.getPcname(), computer.getLoggedUser().getUserLogin(), LocalDate.now(), null));
    }

    @Transactional
    void updateOSAndUserAndProcessor(ComputerEntity computer) {
        OSEntity byOsname = osRepository.findByOsname(computer.getOperatingSystem().getOsname());
        ComputerUserEntity byUserLogin = pcUserRepository.findByUserLogin(computer.getLoggedUser().getUserLogin());
        ProcessorEntity byProcname = processorRepository.findByProcname(computer.getProcessor().getProcname());

        if (byOsname == null) {
            osRepository.save(computer.getOperatingSystem());
        } else computer.setOperatingSystem(byOsname);
        if (byUserLogin == null) {
            pcUserRepository.save(computer.getLoggedUser());
        } else computer.setLoggedUser(byUserLogin);
        if (byProcname == null) {
            processorRepository.save(computer.getProcessor());
        } else computer.setProcessor(byProcname);
    }
}
