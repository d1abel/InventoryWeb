package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.OSEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class OperatingSystem implements ComputerParametersService {

    private Collection<OSEntity> operatingSystems = new ArrayList<>();

    @Override
    public void readRow(ComputerEntity pc, String row) {
        OSEntity osEntity = null;
        for (OSEntity os : operatingSystems) {
            if (row.equalsIgnoreCase(os.getOsname())) {
                osEntity = os;
                pc.setOperatingSystem(os);
            }
        }
        if (osEntity == null) {
            osEntity = new OSEntity(row);
            operatingSystems.add(osEntity);
            pc.setOperatingSystem(osEntity);
        }
        osEntity.getPcs().add(pc);
    }
}
