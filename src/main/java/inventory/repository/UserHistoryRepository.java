package inventory.repository;

import inventory.domain.entity.UserHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserHistoryEntity, Integer> {

    Optional<UserHistoryEntity> findByPcAndCurrentuserAndEndDate(String pc, String currentuser, String enddate);

}
