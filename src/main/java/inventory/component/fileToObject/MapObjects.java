package inventory.component.fileToObject;

import inventory.component.config.InvConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MapObjects {

    private Map<String, Object> mapObjects;

    {
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("os.key"), new OperatinSystem());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcname.key"), new ComputerName());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcuser.key"), new ComputerUser());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcmb.key"), new Motherboard());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcproc.key"), new Processor());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcmbchip.key"), new Chipset());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcram.key"), new RAM());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcdisplay.key"), new Display());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pchdd.key"), new HDD());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pchddspace.key"), new HDDSpace());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("pcinetaddr.key"), new InetAddress());
        mapObjects.put(InvConfig.getInstance().getConfig().getSettings().get("macaddr.key"), new MACAddress());
    }

}
