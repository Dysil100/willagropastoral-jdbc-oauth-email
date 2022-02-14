package duo.cmr.willagropastoral.boundedContexts.finances.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoFinancesRepository extends CrudRepository<FinanceEntity, Long> {

}
