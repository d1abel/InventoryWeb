package inventory.repository;

import inventory.domain.entity.OsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends CrudRepository<OsEntity, Integer> {

    OsEntity findByOsname(String osname);

}
