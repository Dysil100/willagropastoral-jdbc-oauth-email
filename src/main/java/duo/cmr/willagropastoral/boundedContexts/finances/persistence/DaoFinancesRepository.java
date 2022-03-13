package duo.cmr.willagropastoral.boundedContexts.finances.persistence;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface DaoFinancesRepository extends CrudRepository<FinanceEntity, Long> {
    List<FinanceEntity> findAllByProjectName(String projectName);


    @Transactional
    @Modifying
    @Query("UPDATE finance  SET summe = :summe, description = :description, project_name = :projectName WHERE id = :id;")
    void updateById(@Param("id") Long id, @Param("summe") Double summe, @Param("description") String description, @Param("projectName") String projectName);
}
