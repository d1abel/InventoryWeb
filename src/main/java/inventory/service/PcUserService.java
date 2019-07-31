package inventory.service;


import inventory.domain.entity.PcUserEntity;

public interface PcUserService {

    PcUserEntity createOrUpdate(String userLogin);

}
