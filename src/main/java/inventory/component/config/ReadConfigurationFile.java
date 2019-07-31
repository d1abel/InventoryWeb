package inventory.component.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Component
public class ReadConfigurationFile {

    private static final String CONFIG_FILE_NAME = "configuration.properties";

    private Map<String, String> settings = new HashMap<>();

    @PostConstruct
    public void setupMapOfSettings() {
        settings.put("reports.dir", "");
        settings.put("operatingSystem.key", "");
        settings.put("computerName.key", "");
        settings.put("computerUser.key", "");
        settings.put("motherboard.key", "");
        settings.put("processor.key", "");
        settings.put("chipset.key", "");
        settings.put("RAM.key", "");
        settings.put("display.key", "");
        settings.put("HDD.key", "");
        settings.put("freeSpaceHDD.key", "");
        settings.put("inetAddress.key", "");
        settings.put("MACAddress.key", "");
        loadConfig();
    }

    @SneakyThrows
    private void loadConfig() {

            ClassLoader classLoader = ReadConfigurationFile.class.getClassLoader();

            URI uri = Optional.ofNullable(classLoader.getResource(CONFIG_FILE_NAME))
                    .orElseThrow(() -> new RuntimeException("Could not find properties file!"))
                    .toURI();

        File configFile = new File(uri);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile.getAbsoluteFile())))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] settings = line.split("=");
                for (Map.Entry<String, String> item : this.settings.entrySet()) {
                    if (item.getKey().equalsIgnoreCase(settings[0])) {
                        item.setValue(settings[1]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not find properties file!");
        }
    }

}
