package inventory.component.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

public class ConfigLoader {

    private static final String CONFIG_FILE_NAME = "configuration.properties";

    static Config LoadConfig() {

        try {
            ClassLoader classLoader = InvConfig.class.getClassLoader();

            URI uri = Optional.ofNullable(classLoader.getResource(CONFIG_FILE_NAME))
                    .orElseThrow(() -> new RuntimeException("Could not find properties file!"))
                    .toURI();

            File configFile = new File(uri);
            return readConfig(configFile);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static Config readConfig(File configFile) {
        Config config = new Config();
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] settings = line.split("=");
                for (Map.Entry<String, String> item : config.getSettings().entrySet()) {
                    if (item.getKey().equalsIgnoreCase(settings[0])) {
                        item.setValue(settings[1]);
                    }
                }
            }
            return config;
        } catch (Exception e) {
            throw new RuntimeException("heeeey!");
        }
    }
}
