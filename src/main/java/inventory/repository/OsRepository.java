package inventory.repository;

import inventory.domain.entity.OSEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends CrudRepository<OSEntity, Integer> {

    OSEntity findByOsname(String osname);

}
