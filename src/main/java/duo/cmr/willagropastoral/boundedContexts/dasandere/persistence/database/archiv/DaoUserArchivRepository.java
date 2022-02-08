package duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.archiv;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DaoUserArchivRepository extends CrudRepository<UserArchivEntity, Long> {
    @Override
    Optional<UserArchivEntity> findById(Long aLong);

    Optional<UserArchivEntity> findByEmail(String email);
}