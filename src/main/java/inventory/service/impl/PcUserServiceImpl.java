package inventory.service.impl;

import inventory.domain.entity.PcUserEntity;
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
    public PcUserEntity createOrUpdate(String userLogin) {
        PcUserEntity byUserLogin = pcUserRepository.findByUserLogin(userLogin);
        if (byUserLogin != null) {
            return byUserLogin;
        } else {
            byUserLogin = new PcUserEntity(userLogin);
            pcUserRepository.save(byUserLogin);
            return byUserLogin;
        }
    }
}
