package inventory.service.impl;

import inventory.domain.entity.ComputerUserEntity;
import inventory.repository.PcUserRepository;
import inventory.service.PcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PcUserServiceImpl implements PcUserService {

    @Autowired
    private final PcUserRepository pcUserRepository;

    public PcUserServiceImpl(PcUserRepository pcUserRepository) {
        this.pcUserRepository = pcUserRepository;
    }

    @Override
    public ComputerUserEntity createOrUpdate(String userLogin) {
        ComputerUserEntity byUserLogin = pcUserRepository.findByUserLogin(userLogin);
        if (byUserLogin != null) {
            return byUserLogin;
        } else {
            byUserLogin = new ComputerUserEntity(userLogin);
            pcUserRepository.save(byUserLogin);
            return byUserLogin;
        }
    }
}
