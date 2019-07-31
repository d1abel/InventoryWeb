package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import org.springframework.stereotype.Component;

@Component
public class RAM implements FileService {

    @Override
    public void readRow(ComputerEntity pc, String row) {
        pc.setMemory(row);
    }
}
