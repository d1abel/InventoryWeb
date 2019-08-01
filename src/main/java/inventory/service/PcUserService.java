package inventory.service;


import inventory.domain.entity.ComputerUserEntity;

public interface PcUserService {

    ComputerUserEntity createOrUpdate(String userLogin);

}
