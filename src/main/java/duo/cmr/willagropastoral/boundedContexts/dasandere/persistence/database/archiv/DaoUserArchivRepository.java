package duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.archiv;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true)
public interface DaoUserArchivRepository extends CrudRepository<UserArchivEntity, Long> {
    @Override
    Optional<UserArchivEntity> findById(Long aLong);

    Optional<UserArchivEntity> findByEmail(String email);
}
