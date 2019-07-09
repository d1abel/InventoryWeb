package inventory.component.config;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class Config {

    private Map<String, String> settings = new HashMap<>();

    {
        settings.put("reports.dir", "");
        settings.put("os.key", "");
        settings.put("pcname.key", "");
        settings.put("pcuser.key", "");
        settings.put("pcmb.key", "");
        settings.put("pcproc.key", "");
        settings.put("pcmbchip.key", "");
        settings.put("pcram.key", "");
        settings.put("pcdisplay.key", "");
        settings.put("pchdd.key", "");
        settings.put("pchddspace.key", "");
        settings.put("pcinetaddr.key", "");
        settings.put("macaddr.key", "");
    }
}
