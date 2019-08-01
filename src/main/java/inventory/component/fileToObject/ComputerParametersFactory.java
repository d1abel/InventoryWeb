package inventory.component.fileToObject;

import inventory.component.config.ReadConfigurationFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class ComputerParametersFactory {


    @Autowired
    private Chipset chipset;
    @Autowired
    private ComputerUser computerUser;
    @Autowired
    private OperatingSystem operatingSystem;
    @Autowired
    private ComputerName computerName;
    @Autowired
    private Motherboard motherboard;
    @Autowired
    private Processor processor;
    @Autowired
    private RAM ram;
    @Autowired
    private Display display;
    @Autowired
    private HDD hdd;
    @Autowired
    private HDDSpace hddSpace;
    @Autowired
    private InetAddress inetAddress;
    @Autowired
    private MACAddress macAddress;

    private final ReadConfigurationFile configurationFile;

    private HashMap<String, FileService> computerParameters = new HashMap<>();

    @Autowired
    public ComputerParametersFactory(ReadConfigurationFile configurationFile) {
        this.configurationFile = configurationFile;
    }

    @PostConstruct
    public void setupMapOfComputerParameters() {
        computerParameters.put(configurationFile.getSettings().get("computerName.key"), computerName);
        computerParameters.put(configurationFile.getSettings().get("computerUser.key"), computerUser);
        computerParameters.put(configurationFile.getSettings().get("motherboard.key"), motherboard);
        computerParameters.put(configurationFile.getSettings().get("operatingSystem.key"), operatingSystem);
        computerParameters.put(configurationFile.getSettings().get("processor.key"), processor);
        computerParameters.put(configurationFile.getSettings().get("chipset.key"), chipset);
        computerParameters.put(configurationFile.getSettings().get("RAM.key"), ram);
        computerParameters.put(configurationFile.getSettings().get("display.key"), display);
        computerParameters.put(configurationFile.getSettings().get("HDD.key"), hdd);
        computerParameters.put(configurationFile.getSettings().get("freeSpaceHDD.key"), hddSpace);
        computerParameters.put(configurationFile.getSettings().get("inetAddress.key"), inetAddress);
        computerParameters.put(configurationFile.getSettings().get("MACAddress.key"), macAddress);
    }

    public FileService getParameter(String key) {
        return computerParameters.get(key);
    }

}