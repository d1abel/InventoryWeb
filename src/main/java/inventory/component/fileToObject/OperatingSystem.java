package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.OsEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class OperatingSystem implements FileService {

    private Collection<OsEntity> operatingSystems = new ArrayList<>();

    @Override
    public void readRow(ComputerEntity pc, String row) {
        OsEntity osEntity = null;
        for (OsEntity os : operatingSystems) {
            if (row.equalsIgnoreCase(os.getOsname())) {
                osEntity = os;
                pc.setOperatingSystem(os);
            }
        }
        if (osEntity == null) {
            osEntity = new OsEntity(row);
            operatingSystems.add(osEntity);
            pc.setOperatingSystem(osEntity);
        }
        osEntity.getPcs().add(pc);
    }
}
