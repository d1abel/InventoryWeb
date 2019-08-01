package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import org.springframework.stereotype.Component;

@Component
public class ComputerName implements ComputerParametersService {

    private String name;

    @Override
    public void readRow(ComputerEntity pc, String row) {
        pc.setPcname(row);
    }
}
