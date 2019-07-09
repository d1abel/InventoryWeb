package inventory.repository;

import inventory.domain.entity.UserHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserHistoryEntity, Integer> {

    UserHistoryEntity findByPcAndCurrentuserAndEndDate(String pc, String currentuser, String enddate);

}
