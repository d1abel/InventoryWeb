package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.ComputerUserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ComputerUser implements ComputerParametersService {

    private Collection<ComputerUserEntity> users = new ArrayList<>();

    @Override
    public void readRow(ComputerEntity pc, String row) {
        ComputerUserEntity userr = null;
        for (ComputerUserEntity user : users) {
            if (row.equalsIgnoreCase(user.getUserLogin())) {
                userr = user;
                pc.setLoggedUser(user);
            }
        }
        if (userr == null) {
            userr = new ComputerUserEntity(row);
            users.add(userr);
            pc.setLoggedUser(userr);
        }
        userr.getPcs().add(pc);
    }
}
