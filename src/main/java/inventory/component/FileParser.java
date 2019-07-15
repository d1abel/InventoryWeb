package inventory.component;

import inventory.component.config.InvConfig;
import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.OsEntity;
import inventory.domain.entity.PcUserEntity;
import inventory.domain.entity.ProcessorEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Component
public class FileParser {

    private Collection<ComputerEntity> computers = new ArrayList<>();
    private Collection<PcUserEntity> users = new ArrayList<>();
    private Collection<OsEntity> opSysCollection = new ArrayList<>();
    private Collection<ProcessorEntity> processors = new ArrayList<>();

    @SneakyThrows
    private Collection<File> scanDir() {
        String filepath = InvConfig.getInstance().getConfig().getSettings().get("reports.dir");

        return Files.walk(Paths.get(filepath), 1)
                .map(Path::toFile)
                .filter(File::isFile)
                .collect(Collectors.toList());
    }

    private void getComputers() {
        computers.clear();
        Collection<File> reports = scanDir();
        for (File file : reports) {
            Thread thread = new Thread(new ThreadCreator(file));
            thread.start();
        }
    }

    @SneakyThrows
    void read(File file) {
        ComputerEntity pc = new ComputerEntity();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "cp1251"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("=");
                for (Map.Entry<String, String> items : InvConfig.getInstance().getConfig().getSettings().entrySet()) {
                    if (split[0].contains(items.getValue())) {
                        switch (items.getKey()) {
                            case "os.key":
                                if (getOS(split[1]) == null) {
                                    OsEntity opSys = new OsEntity(pc);
                                    opSys.setOsname(split[1]);
                                    pc.setOperatingSystem(opSys);
                                    opSysCollection.add(opSys);
                                } else pc.setOperatingSystem(getOS(split[1]));
                                break;
                            case "pcname.key":
                                pc.setPcname(split[1]);
                                break;
                            case "pcuser.key":
                                if (getUser(split[1]) == null) {
                                    PcUserEntity pcUser = new PcUserEntity(pc);
                                    pcUser.setUserLogin(split[1].toLowerCase());
                                    pc.setLoggedUser(pcUser);
                                    users.add(pcUser);
                                } else pc.setLoggedUser(getUser(split[1]));
                                break;
                            case "pcmb.key":
                                pc.setMotherboard(split[1]);
                                break;
                            case "pcproc.key":
                                if (getProc(split[1]) == null) {
                                    ProcessorEntity processor = new ProcessorEntity(pc);
                                    processor.setProcname(split[1].toLowerCase());
                                    pc.setProcessor(processor);
                                    processors.add(processor);
                                } else pc.setProcessor(getProc(split[1]));
                                break;
                            case "pcmbchip.key":
                                pc.setChipset(split[1]);
                                break;
                            case "pcram.key":
                                pc.setMemory(split[1]);
                                break;
                            case "pcdisplay.key":
                                if (pc.getDisplay() == null) {
                                    pc.setDisplay(split[1]);
                                } else pc.setDisplay(pc.getDisplay() + ", " + split[1]);
                                break;
                            case "pchdd.key":
                                if (pc.getHdd() == null) {
                                    pc.setHdd(split[1]);
                                } else pc.setHdd(pc.getHdd() + ", " + split[1]);
                                break;
                            case "pchddspace.key":
                                if (pc.getHddSpace() == null) {
                                    pc.setHddSpace(split[1]);
                                } else pc.setHddSpace(pc.getHddSpace() + ", " + split[1]);
                                break;
                            case "pcinetaddr.key":
                                pc.setInetAddr(split[1]);
                                break;
                            case "macaddr.key":
                                pc.setMac(split[1]);
                                break;
                        }
                    }
                }
            }
        }
        computers.add(pc);
    }

    private OsEntity getOS(String name) {
        OsEntity os = null;
        for (OsEntity oz : opSysCollection) {
            if (name.equalsIgnoreCase(oz.getOsname())) {
                os = oz;
            }
        }
        return os;
    }

    private PcUserEntity getUser(String login) {
        PcUserEntity userr = null;
        for (PcUserEntity user : users) {
            if (login.equalsIgnoreCase(user.getUserLogin())) {
                userr = user;
            }
        }
        return userr;
    }

    private ProcessorEntity getProc(String procname) {
        ProcessorEntity processor = null;
        for (ProcessorEntity proc : processors) {
            if (procname.equalsIgnoreCase(proc.getProcname())) {
                processor = proc;
            }
        }
        return processor;
    }

    Collection<ComputerEntity> getComputersCollection() {
        return computers;
    }

    void readFiles() {
        getComputers();
    }

    private static void getUsersInfo() {
        //TODO: connect to ldap and get info
    }

}
