package inventory.repository;

import inventory.domain.entity.PcUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcUserRepository extends CrudRepository<PcUserEntity, Integer> {

    PcUserEntity findByUserLogin(String userLogin);

}
