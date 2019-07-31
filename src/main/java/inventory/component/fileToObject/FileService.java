package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;

public interface FileService {

    void readRow(ComputerEntity pc, String row);

}
