package inventory.repository;

import inventory.domain.entity.ProcessorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends CrudRepository<ProcessorEntity, Integer> {

    ProcessorEntity findByProcname(String procname);

}
