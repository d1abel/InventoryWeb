package inventory.repository;

import inventory.domain.entity.ComputerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends CrudRepository<ComputerEntity, Integer> {

    ComputerEntity findByMac(String mac);

}
