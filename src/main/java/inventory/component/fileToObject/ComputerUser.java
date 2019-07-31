package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.PcUserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ComputerUser implements FileService {

    private Collection<PcUserEntity> users = new ArrayList<>();

    @Override
    public void readRow(ComputerEntity pc, String row) {
        PcUserEntity userr = null;
        for (PcUserEntity user : users) {
            if (row.equalsIgnoreCase(user.getUserLogin())) {
                userr = user;
                pc.setLoggedUser(user);
            }
        }
        if (userr == null) {
            userr = new PcUserEntity(row);
            users.add(userr);
        }
        userr.getPcs().add(pc);
    }
}
