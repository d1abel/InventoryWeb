package inventory.component;

import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.UserHistoryEntity;
import inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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

    @Transactional
    public void updateDB() {

        fileParser.readFiles();
        for (ComputerEntity computer : fileParser.getComputersCollection()) {
            if (computerRepository.findByMac(computer.getMac()) == null) {
                if (osRepository.findByOsname(computer.getOperatingSystem().getOsname()) == null) {
                    osRepository.save(computer.getOperatingSystem());
                }
                if (pcUserRepository.findByUserLogin(computer.getLoggedUser().getUserLogin()) == null) {
                    pcUserRepository.save(computer.getLoggedUser());
                }
                if (processorRepository.findByProcname(computer.getProcessor().getProcname()) == null) {
                    processorRepository.save(computer.getProcessor());
                }
                computerRepository.save(computer);
                userHistoryRepository.save(new UserHistoryEntity(computer.getPcname(), computer.getLoggedUser().getUserLogin(), LocalDate.now(), null));
            } else {
                ComputerEntity entity = computerRepository.findByMac(computer.getMac());
                if (!(computer.getPcname().equalsIgnoreCase(entity.getPcname()))) {
                    if (entity.getLastNames() == null) {
                        entity.setLastNames(entity.getPcname());
                    } else {
                        entity.setLastNames(entity.getLastNames() + ", " + entity.getPcname());
                    }
                    entity.setPcname(computer.getPcname());
                }
//                System.out.println(userHistoryRepository.findByPcAndCurrentuserAndEndDate(entity.getPcname(), entity.getLoggedUser().getUserLogin(), null).getCurrentuser());
                if (!(computer.getLoggedUser().getUserLogin().equalsIgnoreCase(entity.getLoggedUser().getUserLogin()))) {
//                    System.out.println(entity.getPcname());
//                    UserHistoryEntity history = userHistoryRepository.findByPcAndCurrentuserAndEndDate(entity.getPcname(), entity.getLoggedUser().getUserLogin(), null);
//                    history.setEndDate(LocalDate.now());
//                    userHistoryRepository.save(history);
                    userHistoryRepository.save(new UserHistoryEntity(computer.getPcname(), computer.getLoggedUser().getUserLogin(), LocalDate.now(), null));
                    if (pcUserRepository.findByUserLogin(computer.getLoggedUser().getUserLogin()) != null) {
                        entity.setLoggedUser(pcUserRepository.findByUserLogin(computer.getLoggedUser().getUserLogin()));
                    } else pcUserRepository.save(computer.getLoggedUser());
                }
                computerRepository.save(entity);
            }
        }
    }
}
