package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import org.springframework.stereotype.Component;

@Component
public class HDD implements ComputerParametersService {

    @Override
    public void readRow(ComputerEntity pc, String row) {
        pc.setHdd(row);
    }
}
