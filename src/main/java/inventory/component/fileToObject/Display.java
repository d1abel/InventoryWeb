package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import org.springframework.stereotype.Component;

@Component
public class Display implements ComputerParametersService {

    @Override
    public void readRow(ComputerEntity pc, String row) {
        pc.setDisplay(row);
    }
}
