package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;

public interface ComputerParametersService {

    void readRow(ComputerEntity pc, String row);

}
