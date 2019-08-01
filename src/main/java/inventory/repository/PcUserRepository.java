package inventory.repository;

import inventory.domain.entity.ComputerUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcUserRepository extends CrudRepository<ComputerUserEntity, Integer> {

    ComputerUserEntity findByUserLogin(String userLogin);

}
